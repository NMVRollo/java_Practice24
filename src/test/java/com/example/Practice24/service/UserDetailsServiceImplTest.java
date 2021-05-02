package com.example.Practice24.service;

import com.example.Practice24.models.Dog;
import com.example.Practice24.models.UserDetailsModel;
import com.example.Practice24.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Captor
    ArgumentCaptor<UserDetailsModel> captor;

    @Test
    void save() {
        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setUsername("admin");
        userDetailsModel.setPassword("123");

        UserDetailsServiceImpl detailsService = new UserDetailsServiceImpl(new BCryptPasswordEncoder(), accountRepository);
        detailsService.save(userDetailsModel);
        verify(accountRepository).save(captor.capture());
        UserDetailsModel captured = captor.getValue();

        assertEquals("admin", captured.getUsername());
    }

    @Test
    void loadUserByUsername() {
        UserDetailsModel userDetailsModel = new UserDetailsModel();
        userDetailsModel.setUsername("admin");

        UserDetailsServiceImpl detailsService = new UserDetailsServiceImpl(new BCryptPasswordEncoder(), accountRepository);

        when(accountRepository.findByUsername("admin")).thenReturn(userDetailsModel);
        assertEquals("admin", detailsService.loadUserByUsername("admin").getUsername());
    }
}