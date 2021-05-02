package com.example.Practice24.service;

import com.example.Practice24.models.UserDetailsModel;
import com.example.Practice24.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository repository;

    @Autowired
    public UserDetailsServiceImpl(PasswordEncoder passwordEncoder, AccountRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public void save(UserDetailsModel userDetailsModel) {
        userDetailsModel.setRole("user");
        userDetailsModel.setPassword(passwordEncoder.encode(userDetailsModel.getPassword()));
        repository.save(userDetailsModel);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }
}
