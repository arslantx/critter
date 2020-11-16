package com.udacity.jdnd.course3.critter.service;

import java.util.List;
import java.util.Optional;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;

    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet findById(Long petId) {
        Optional<Pet> optionalPet = petRepository.findById(petId);
        if (optionalPet.isPresent()) {
            return optionalPet.get();
        }
        return null;
    }
    
    public List<Pet> findAllPets() {
        return petRepository.findAll();
    }

    public List<Pet> findPetsByOwnerId(Long ownerId) {
        return petRepository.findPetsByOwnerId(ownerId);
    }
    
    public List<Pet> findAllByIds(List<Long> ids) {
        return petRepository.findAllById(ids);
    }
}
