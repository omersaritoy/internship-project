package com.example.Example.entities;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "department")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
   // @JsonIgnore//sadeleştirme sorunları olabilir ,büyük verilerde sıkıntı yaşanılabilir,karmaşık ve okunaklı olmayabilir
    // @JsonManagedReference // Yönetilen referans
    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL, fetch = FetchType.LAZY)//ihtiyacı olduğunda getirir
    //eager ise ihityaç olup olmaması önemli değil bütün ilişkileri getirir
    private List<Employee> employees;
    private Boolean isDeleted = false;
    private Boolean isActive=true;

}
