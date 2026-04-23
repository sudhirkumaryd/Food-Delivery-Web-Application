package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // REGISTER
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        if (userRepository.findByEmail(user.getEmail()) != null) {
            response.put("success", false);
            response.put("message", "User already exists");
            return response;
        }

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        response.put("success", true);
        response.put("token", token);

        return response;
    }

    // LOGIN
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> response = new HashMap<>();

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null || 
            !existingUser.getPassword().equals(user.getPassword())) {

            response.put("success", false);
            response.put("message", "Invalid credentials");
            return response;
        }

        String token = UUID.randomUUID().toString();

        response.put("success", true);
        response.put("token", token);

        return response;
    }
}