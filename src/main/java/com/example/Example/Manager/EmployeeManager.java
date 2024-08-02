package com.example.Example.Manager;

import com.example.Example.Dtos.EmployeeDto;
import com.example.Example.annotations.Unique;
import com.example.Example.annotations.UniqueType;
import com.example.Example.entities.Department;
import com.example.Example.entities.Employee;

import com.example.Example.mernis.HWGKPSPublicSoap;
import com.example.Example.repository.IDepartmentRepo;
import com.example.Example.services.IEmployeeService;
import com.example.Example.services.ILoggerService;
import com.example.Example.utilities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.example.Example.repository.IEmployeeRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmployeeManager implements IEmployeeService {
    private final IEmployeeRepo employeeRepo;
    private final IDepartmentRepo departmentRepo;
    private final EmailSenderManager emailSenderManager;
    private final RedisTemplate<String, Object> redisTemplate;
    private final VerificationService verificationService;
    private final ILoggerService loggerService;

    @Autowired
    public EmployeeManager(IEmployeeRepo employeeRepo, IDepartmentRepo departmentRepo, EmailSenderManager emailSenderManager, RedisTemplate<String, Object> redisTemplate, VerificationService verificationService, ILoggerService loggerService) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.emailSenderManager = emailSenderManager;
        this.redisTemplate = redisTemplate;
        this.verificationService = verificationService;
        this.loggerService = loggerService;

    }

    public EmployeeDto getDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        return employeeDto;
    }

    @Override
    public DataResult<EmployeeDto> getEmployeeById(Long id) {

        Employee employee = employeeRepo.findById(id).orElse(null);
        if (employee == null) {
            return new DataResult<>(false, "Böyle bir eleman yok");
        }
        EmployeeDto employeeDto = getDto().convertToDto(employee);
        return new SuccessDataResult<>(employeeDto);
    }

    @Override
    public DataResult<List<EmployeeDto>> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        List<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees) {
            if (!employee.getIsDeleted())
                employeeDtos.add(getDto().convertToDto(employee));
        }
        return new SuccessDataResult<>(employeeDtos);
    }

    @Override
    @Unique({UniqueType.Email, UniqueType.UserName})
    public Result saveEmployee(EmployeeDto employeeDto) throws Exception {
        Employee employee = null;

        HWGKPSPublicSoap server=new HWGKPSPublicSoap();
        boolean isReal= server.TCKimlikNoDogrula(Long.valueOf(employeeDto.getIdentityNumber()), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getBirthOfYear());
        if(!isReal)
            return new ErrorResult("Kimlik bilgileri Doğrulanamadı");

        Department department = departmentRepo.findById(employeeDto.getDepartmentId()).orElse(null);
        // Eğer department null ise veya silinmiş veya aktif değilse hata mesajı döndür
        if (department == null || department.getIsDeleted() || !department.getIsActive()) {
            return new Result("Lütfen geçerli bir departman giriniz");
        }

        Employee emp = getDto().convertToEntity(employeeDto);

        emp.setDepartment(department);

        if (!employeeDto.getPassword().equals(employeeDto.getRepeatPassword()))
            return new Result("Password does not match");


        employee = employeeRepo.save(emp);
        loggerService.log("Create", emp.getFirstName() + " " + emp.getLastName() + "çalışanı eklendi", "Employee", employee.getId());
        String verificationCode = verificationService.generateVerificationCode();
        redisTemplate.opsForValue().set("verificationCode:" + employee.getId(), verificationCode, 10, TimeUnit.MINUTES);
        emailSenderManager.sendMail(employee.getEmail(), employee.getUserName(), verificationCode);


        return new DataResult<>(true, "Employee Başarılı bir Şekilde Eklendi", employeeDto.convertToDto(employee));

    }

    @Override
    public Result getEmployeeByUserName(String userName) {
        Employee employee = employeeRepo.findByUserName(userName);

        if (employee != null && !employee.getIsDeleted()) {
            EmployeeDto employeeDto = getDto().convertToDto(employee);
            return new SuccessDataResult<>("Eleman bulundu", employeeDto);
        }
        return new DataResult<>(false, "Böyle bir eleman yok");

    }

    @Override
    @Unique({UniqueType.EmailForUpdate,UniqueType.UserNameForUpdate})
    public Result updateEmployee(EmployeeDto employeeDto, Long id) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setBirthOfYear(employeeDto.getBirthOfYear());
        Department department = departmentRepo.findById(employeeDto.getDepartmentId()).orElse(null);
        if (department == null || department.getIsDeleted() || !department.getIsActive())
            return new Result("Lütfen geçerli bir departman giriniz");

        employee.setDepartment(department);
        if (!employee.getUserName().equals(employeeDto.getUserName())){
            employee.setUserName(employeeDto.getUserName());
        }


        if (!employee.getEmail().equals(employeeDto.getEmail()))
            employee.setEmail(employeeDto.getEmail());
        employeeRepo.save(employee);
        employeeDto.converToDtoForUpdate(employee);
        loggerService.log("update", employee.getFirstName() + " " + employee.getLastName() + " çalışanı güncellendi", "Employee", employee.getId());

        return new DataResult<>(true, "Employee Güncelendi", employeeDto);
    }

    @Override
    public Result deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id).orElse(null);
        employee.setIsDeleted(true);
        employeeRepo.save(employee);
        loggerService.log("Delete", employee.getFirstName() + " " + employee.getLastName() + " çalışanı silindi", "Employee", employee.getId());

        return new SuccessResult("Başarılı bir şekilde silindi");
    }

    @Override
    public Result verifyEmployee(Long id, String verificationCode) {
        String storedCode = (String) redisTemplate.opsForValue().get("verificationCode:" + id);
        Employee employee = employeeRepo.findById(id).orElse(null);

        if (storedCode == null) {
            return new Result(false, "Doğrulama kodu bulunamadı veya süresi doldu");
        }
        if (storedCode.equals(verificationCode)) {
            redisTemplate.delete("verificationCode:" + id);
            employee.setVerify(true);
            EmployeeDto employeeDto = getDto().convertToDto(employee);
            employeeRepo.save(employee);
            return new SuccessDataResult<>("Doğrulama başarılı", employeeDto);
        } else {
            return new Result(false, "Doğrulama kodu geçersiz");
        }
    }

}

