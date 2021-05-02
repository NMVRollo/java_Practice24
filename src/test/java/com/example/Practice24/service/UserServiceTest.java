package com.example.Practice24.service;

import com.example.Practice24.models.User;
import com.example.Practice24.repository.UserRepository;
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
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Captor
    ArgumentCaptor<User> captor;

    @Test
    void getUsers() {
        User user = new User();
        user.setFirstName("Vasya");
        User user2 = new User();
        user2.setFirstName("Dima");

        when(userRepository.findAll()).thenReturn(List.of(user, user2));
        UserService userService = new UserService(userRepository);
        assertEquals(2, userService.findAll().size());
        assertEquals("Vasya", userService.findAll().get(0).getFirstName());
    }

    @Test
    void saveOrUpdate() {
        User user = new User();
        user.setFirstName("Vitya");
        UserService userService = new UserService(userRepository);
        userService.saveOrUpdate(user);
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        assertEquals("Vitya", captured.getFirstName());
    }

    @Test
    public void findByFirstName() {
        User user = new User();
        user.setFirstName("Vasya");

        UserService userService = new UserService(userRepository);

        when(userRepository.findAllByFirstName("Vasya")).thenReturn(List.of(user));
        assertEquals(1, userService.findByFirstName("Vasya").size());
        assertEquals("Vasya", userService.findByFirstName("Vasya").get(0).getFirstName());
    }

    @Test
    public void findByLastName() {
        User user = new User();
        user.setLastName("Ivanov");

        UserService userService = new UserService(userRepository);

        when(userRepository.findAllByLastName("Ivanov")).thenReturn(List.of(user));
        assertEquals(1, userService.findByLastName("Ivanov").size());
        assertEquals("Ivanov", userService.findByLastName("Ivanov").get(0).getLastName());
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(2L);

        UserService userService = new UserService(userRepository);

        when(userRepository.findOneById(2L)).thenReturn(user);
        assertEquals(2L, userService.findById(2L).getId());
    }

}