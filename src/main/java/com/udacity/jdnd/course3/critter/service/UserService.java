package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.User;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public <T extends User> List<T> findAllUsers() {
        return (List<T>) userRepository.findAll();
    }

    public <T extends User> T save(T user) {
        return userRepository.save(user);
    }

    public <T extends User> T findById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return (T) optional.get();
        }
        return null;
    }

    public <T extends User> List<T> findAllByIdList(List<Long> idList) {
        return (List<T>) userRepository.findAllById(idList);
    }

    public void setEmployeeAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = findById(employeeId);
        if (employee == null) {
            throw new UnsupportedOperationException("No employee found with id: " + employeeId);
        }
        employee.setDaysAvailable(daysAvailable);
        save(employee);
    }

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, LocalDate date) {
        return userRepository.findEmployeeForService(skills, date.getDayOfWeek());
    }

    public Customer findCustomerByPet(Long petId) {
        return userRepository.findDistinctByPetsId(petId);
    }
}
