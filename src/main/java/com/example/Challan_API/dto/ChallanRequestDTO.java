package com.example.Challan_API.dto;

import java.time.LocalDateTime;

public class ChallanRequestDTO {
    private String violationType;
    private String vehicleNumber;
    private String location;
    private Double amount;
    private LocalDateTime violationDate;
    private Long userId;
    private String description;
    
    public ChallanRequestDTO() {}
    
    // Getters and Setters
    public String getViolationType() {
        return violationType;
    }
    
    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }
    
    public String getVehicleNumber() {
        return vehicleNumber;
    }
    
    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public Double getAmount() {
        return amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    public LocalDateTime getViolationDate() {
        return violationDate;
    }
    
    public void setViolationDate(LocalDateTime violationDate) {
        this.violationDate = violationDate;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}
