package com.example.Example.Manager;

import com.example.Example.Dtos.DepartmentDto;
import com.example.Example.Dtos.EmployeeDto;
import com.example.Example.annotations.Unique;
import com.example.Example.annotations.UniqueType;
import com.example.Example.entities.Department;
import com.example.Example.services.ILoggerService;
import com.example.Example.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Example.repository.IDepartmentRepo;
import com.example.Example.services.IDepartmentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentManager implements IDepartmentService {
    private final IDepartmentRepo departmentRepo;//DI nesenenin bağımlılığından kurtulur
    private final ILoggerService loggerService;

    @Autowired
    public DepartmentManager(IDepartmentRepo departmentRepo, ILoggerService loggerService) {
        this.departmentRepo = departmentRepo;
        this.loggerService = loggerService;
    }

    public DepartmentDto getDto() {
        DepartmentDto departmentDto = new DepartmentDto();
        return departmentDto;
    }

    @Override
    @Unique(UniqueType.Code)
    //@Unique-anatasyon yazılıcak öncelikle bir interface yaz sonra 
    public DataResult<DepartmentDto> saveDepartment(DepartmentDto departmentDto) {
        Department dep = departmentRepo.save(getDto().convertToEntity(departmentDto));
        if (dep != null)
            return new SuccessDataResult<DepartmentDto>(dep);
        dep.setCode(dep.getCode());
        DepartmentDto dto = getDto().convertToDto(dep);
        loggerService.log("Create",dep.getName()+" Departman eklendi","Department",dep.getId());

        return new SuccessDataResult<>("Departmant oluşturldu", dto);
    }


    @Override
    public DataResult<DepartmentDto> getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id).get();
        if (department == null)
            return new DataResult<>(false, "Departman boş");
        DepartmentDto dto = getDto().convertToDto(department);
        return new SuccessDataResult<>(dto);
    }

    //burayı kontrol etmeyi unutma
    @Override
    public DataResult<List<DepartmentDto>> getAllDepartments() {
        List<Department> departments = departmentRepo.findAll();
        List<DepartmentDto> dtos = new ArrayList<>();
        List<EmployeeDto> empDtoList = new ArrayList<>();
        for (Department department : departments) {

            if (!department.getIsDeleted()) {
                empDtoList = getAllEmployees(department.getId());
                dtos.add(getDto().convertToDtoList(department, empDtoList));
                empDtoList.remove(empDtoList);
            }
        }
        return new SuccessDataResult<>(dtos);

    }

    public List<EmployeeDto> getAllEmployees(Long departmentId) {
        EmployeeDto employeeDto = new EmployeeDto();
        Department department = departmentRepo.findById(departmentId).get();
        return employeeDto.convertToDtoList(department.getEmployees());
    }

    @Override
    @Unique(UniqueType.Code)
    public DataResult<DepartmentDto> updateDepartment(Long id, DepartmentDto dto) {
        Department department = departmentRepo.findById(id).orElse(null);
        department.setName(dto.getName());

        department.setCode(dto.getCode());


        departmentRepo.save(department);
        dto = getDto().convertToDto(department);
        loggerService.log("Create",department.getName()+" Departman güncellendi","Department",department.getId());
        return new SuccessDataResult<>("Başarılı bir şeekilde güncellendi", dto);
    }

    @Override
    public Result deleteDepartment(Long id) {

        Department department = departmentRepo.findById(id).get();
        if(getAllEmployees(id).stream().count()==0){
            department.setIsDeleted(true);
            department.setIsActive(false);
            departmentRepo.save(department);
            loggerService.log("Create",department.getName()+" Departman silindi","Department",department.getId());
            return new SuccessResult("Depatman başarılıyla silindi");
        }

        return   new ErrorResult("Departman silinmeye uygun değil");
    }
    public DataResult<List<String>> getDepartmentEmployeeCounts() {
        List<Department> departments = departmentRepo.findAll();
        List<String> employeeCounts = new ArrayList<>();
        for (Department department : departments) {
            if(!department.getIsDeleted()&&department.getIsActive()){
                long employeeCount = department.getEmployees().stream().count();
                employeeCounts.add("Departmant ID:"+department.getId()+",Department:"+department.getName()+":,Employees:"+employeeCount);

            }
        }
        return new SuccessDataResult<>(employeeCounts);
    }
}



