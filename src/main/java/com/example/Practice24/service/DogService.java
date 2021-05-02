package com.example.Practice24.service;

import com.example.Practice24.models.Dog;
import com.example.Practice24.models.User;
import com.example.Practice24.repository.DogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class DogService {

    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void saveOrUpdate(Dog dog) {
        dogRepository.save(dog);
    }

    public User getUserByDog(Long dogId) {
        log.info("Find user by dogId " + dogId);
        return dogRepository.findOneById(dogId).getUser();
    }

    public Dog findById(Long id) {
        log.info("Find dog by id " + id);
        return dogRepository.findOneById(id);
    }

    public List<Dog> findByName(String name) {
        log.info("Find dog by name " + name);
        return dogRepository.findAllByName(name);
    }

    public List<Dog> findByBreed(String breed) {
        log.info("Find dog by breed " + breed);
        return dogRepository.findAllByBreed(breed);
    }

    public List<Dog> getAllDogs() {
        log.info("Find all dogs.");
        return dogRepository.findAll();
    }
}
