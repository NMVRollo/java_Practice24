package com.example.Practice24.service;

import com.example.Practice24.models.Dog;
import com.example.Practice24.models.MethodInvoker;
import com.example.Practice24.models.User;
import com.example.Practice24.repository.DogRepository;
import com.example.Practice24.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.NotCompliantMBeanException;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchedulerServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private DogRepository dogRepository;

    @Test
    void doScheduledTask() throws NotCompliantMBeanException {
        User user = new User();
        user.setFirstName("Vova");
        user.setFirstName("Popov");
        user.setId(1L);

        Dog dog = new Dog();
        dog.setUser(user);
        dog.setName("Lolly");
        dog.setBreed("Haski");
        dog.setId(1L);
        user.setDogs(List.of(dog));

        when(userRepository.findAll()).thenReturn(List.of(user));
        when(dogRepository.findAll()).thenReturn(List.of(dog));

        SchedulerService schedulerService = new SchedulerService(dogRepository, userRepository, new MethodInvoker());
        schedulerService.doScheduledTask();

        assertTrue(new File("user.txt").delete());
        assertTrue(new File("dog.txt").delete());
    }
}