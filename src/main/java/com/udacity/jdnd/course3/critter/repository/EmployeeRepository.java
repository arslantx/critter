package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;
import javax.transaction.Transactional;
import com.udacity.jdnd.course3.critter.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface EmployeeRepository extends UserRepository<Employee> {
    
    public List<Employee> findByDaysAvailableContaining(DayOfWeek dayOfWeek);
    
}
