package com.udacity.jdnd.course3.critter.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class Schedule {
    
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
      name = "schedule_employee",
      joinColumns = { @JoinColumn(name = "schedule_id")},
      inverseJoinColumns = { @JoinColumn(name = "employee_id")}
    )
    private List<Employee> employees;
    
    @ManyToMany
    @JoinTable(
      name = "schedule_pet",
      joinColumns = { @JoinColumn(name = "schedule_id")},
      inverseJoinColumns = { @JoinColumn(name = "pet_id")}
    )
    private List<Pet> pets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
