package com.example.Challan_API.dto;

public class SignupResponse {

    private String message;
    private String username;
    private String role;

    public SignupResponse(String message, String username, String role) {
        this.message = message;
        this.username = username;
        this.role = role;
    }

    // Getters
    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
    public String getRole() {
        return role;
    }
}
