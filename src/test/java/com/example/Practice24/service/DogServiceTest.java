package com.example.Practice24.service;

import com.example.Practice24.models.Dog;
import com.example.Practice24.models.User;
import com.example.Practice24.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository dogRepository;
    @Captor
    ArgumentCaptor<Dog> captor;

    @Test
    void saveOrUpdate() {
        Dog dog = new Dog();
        dog.setName("Dolly");

        DogService dogService = new DogService(dogRepository);
        dogService.saveOrUpdate(dog);
        verify(dogRepository).save(captor.capture());
        Dog captured = captor.getValue();

        assertEquals("Dolly", captured.getName());
    }

    @Test
    void getUserByDog() {
        Dog dog = new Dog();
        User user = new User();
        user.setFirstName("Vasya");
        dog.setUser(user);

        DogService dogService = new DogService(dogRepository);
        when(dogRepository.findOneById(1L)).thenReturn(dog);

        assertEquals("Vasya", dogService.getUserByDog(1L).getFirstName());
    }

    @Test
    void findById() {
        Dog dog = new Dog();
        dog.setId(2L);

        DogService dogService = new DogService(dogRepository);

        when(dogRepository.findOneById(2L)).thenReturn(dog);
        assertEquals(2L, dogService.findById(2L).getId());
    }

    @Test
    void findByName() {
        Dog dog = new Dog();
        dog.setName("Dolly");

        DogService dogService = new DogService(dogRepository);

        when(dogRepository.findAllByName("Dolly")).thenReturn(List.of(dog));
        assertEquals(1, dogRepository.findAllByName("Dolly").size());
        assertEquals("Dolly", dogService.findByName("Dolly").get(0).getName());
    }

    @Test
    void findByBreed() {
        Dog dog = new Dog();
        dog.setBreed("Haski");

        DogService dogService = new DogService(dogRepository);

        when(dogRepository.findAllByBreed("Haski")).thenReturn(List.of(dog));
        assertEquals(1, dogRepository.findAllByBreed("Haski").size());
        assertEquals("Haski", dogService.findByBreed("Haski").get(0).getBreed());
    }

    @Test
    void getAllDogs() {
        Dog dog = new Dog();
        dog.setName("Dolly");
        Dog dog1 = new Dog();
        dog1.setName("Polly");

        when(dogRepository.findAll()).thenReturn(List.of(dog, dog1));
        DogService dogService = new DogService(dogRepository);
        assertEquals(2, dogService.getAllDogs().size());
        assertEquals("Dolly", dogService.getAllDogs().get(0).getName());
    }
}