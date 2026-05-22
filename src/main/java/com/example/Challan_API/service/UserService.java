package com.example.Challan_API.service;

import com.example.Challan_API.dto.LoginRequest;
import com.example.Challan_API.dto.LoginResponse;
import com.example.Challan_API.dto.SignupRequest;
import com.example.Challan_API.dto.SignupResponse;
import com.example.Challan_API.entity.User;

public interface UserService 
{
	SignupResponse signup(SignupRequest signupRequest);
    LoginResponse authenticate(LoginRequest loginRequest);
    User findByUsername(String username);
    User findById(Long id);
}
