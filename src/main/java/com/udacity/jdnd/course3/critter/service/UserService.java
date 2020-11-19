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
import com.udacity.jdnd.course3.critter.enums.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Customer findCustomerById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            return optionalCustomer.get();
        } else {
            return null;
        }
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        } else {
            return null;
        }
    }

    public List<Employee> findAllEmployeesByIdList(List<Long> idList) {
        return employeeRepository.findAllById(idList);
    }

    public void setEmployeeAvailability(Long employeeId, Set<DayOfWeek> daysAvailable) {
        Employee employee = findEmployeeById(employeeId);
        if (employee == null) {
            throw new UnsupportedOperationException("No employee found with id: " + employeeId);
        }
        employee.setDaysAvailable(daysAvailable);
        saveEmployee(employee);
    }

    public List<Employee> findEmployeeForService(Set<EmployeeSkill> skills, LocalDate date) {
        List<Employee> employees = employeeRepository.findByDaysAvailableContaining(date.getDayOfWeek());
        return employees.stream()
                .filter(e -> e.getSkills().containsAll(skills)).collect(Collectors.toList());
    }

    public Customer findCustomerByPet(Long petId) {
        return customerRepository.findByPetsId(petId);
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
        saveCustomer(customer);
    }
}
