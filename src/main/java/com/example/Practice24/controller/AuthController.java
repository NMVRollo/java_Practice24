package com.example.Practice24.controller;

import com.example.Practice24.models.UserDetailsModel;
import com.example.Practice24.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl service;

    @PostMapping("/reg")
    public String create(@RequestBody UserDetailsModel userDetailsModel) {
        service.save(userDetailsModel);
        return "redirect:/login";
    }
}
