package com.udacity.jdnd.course3.critter.repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.User;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    // public List<Employee> findDistinctBySkillsInAndDaysAvailableContaining(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);
    
    @Query("select u from User u where :dayOfWeek in u.daysAvailable and :skills in u.skills")
    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek);

    public Customer findDistinctByPetsId(Long petId);
    
}
