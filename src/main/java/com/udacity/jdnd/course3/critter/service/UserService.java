package com.udacity.jdnd.course3.critter.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.User;
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.UserRepository;
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
        List<Employee> employees = userRepository.findByDaysAvailableContaining(date.getDayOfWeek());
        return employees.stream()
                .filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }

    public Customer findCustomerByPet(Long petId) {
        return userRepository.findByPetsId(petId);
    }

    public void addPetToCustomer(Pet pet, Customer customer) {
        Set<Pet> pets = customer.getPets();
        if (pets != null)
            pets.add(pet);
        else {
            pets = new HashSet<>();
            pets.add(pet);
        }
        customer.setPets(pets);
        userRepository.save(customer);
    }
}
