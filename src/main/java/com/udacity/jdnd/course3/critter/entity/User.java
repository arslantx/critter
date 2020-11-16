package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
// single table strategy is used to speed up polymorphic queries
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class User {
    
    @Id
    @GeneratedValue
    protected Long id;
    
    protected String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
