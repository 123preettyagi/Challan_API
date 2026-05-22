package com.example.Challan_API.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Challan_API.config.JwtService;
import com.example.Challan_API.dto.LoginRequest;
import com.example.Challan_API.dto.LoginResponse;
import com.example.Challan_API.dto.SignupRequest;
import com.example.Challan_API.dto.SignupResponse;
import com.example.Challan_API.entity.Role;
import com.example.Challan_API.entity.User;
import com.example.Challan_API.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    
    @Override
    public SignupResponse signup(SignupRequest signupRequest) 
    {

        // Check if username exists
        if (userRepository.findByUsername(signupRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        // Create new user object
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setFullName(signupRequest.getFullName());
        user.setEmail(signupRequest.getEmail());
        user.setPhone(signupRequest.getPhone());

        // Convert string role to enum
        Role role = Role.valueOf(signupRequest.getRole().toUpperCase());
        user.setRole(role);

        // Save in DB
        userRepository.save(user);

        return new SignupResponse(
                "User registered successfully!",
                user.getUsername(),
                user.getRole().name()
        );
    }


    
    
    
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        
        // Authenticate user credentials
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // ⚠ FIX: authentication.getPrincipal() returns UserDetails, not User
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found in DB"));

        // Generate JWT token
      //  String jwtToken = jwtService.generateToken(user);
        
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole().name())
                .build();

        String jwtToken = jwtService.generateToken(userDetails);


        return new LoginResponse(
                jwtToken,
                user.getUsername(),
                user.getRole().name(),
                user.getFullName()
        );
    }
    
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
    
    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + id));
    }
}