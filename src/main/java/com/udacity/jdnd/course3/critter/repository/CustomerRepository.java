package com.udacity.jdnd.course3.critter.repository;

import javax.transaction.Transactional;
import com.udacity.jdnd.course3.critter.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CustomerRepository extends UserRepository<Customer> {
    
    public Customer findByPetsId(Long petId);
    
}
