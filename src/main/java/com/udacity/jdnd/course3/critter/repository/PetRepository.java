package com.udacity.jdnd.course3.critter.repository;

import java.util.List;
import com.udacity.jdnd.course3.critter.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    
    @Query("select p from Pet p where p.customer.id = :ownerId")
    public List<Pet> findPetsByOwnerId(Long ownerId);
}
