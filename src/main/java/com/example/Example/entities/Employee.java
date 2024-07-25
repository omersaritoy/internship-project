package com.example.Example.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String userName;
    @Column(name="password")
    private String password;
    @Transient
    private String repeatPassword;
    private String email;
    // @JsonBackReference // Geri referans
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    @Transient//
    private Long departmentId;
    private Boolean verify=false;
    private Boolean isDeleted=false;
}
