package com.example.Challan_API.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;

public class ChallanDTO {
    private Long id;
    private String violationType;
    private String vehicleNumber;
    private String location;
    private Double amount;
    private LocalDateTime violationDate;
    private LocalDateTime paymentDate;
    private String status;
    private Long userId;
    private String userName;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    public ChallanDTO() {}
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    
    private String challanNumber;

 // Add getters and setters
 public String getChallanNumber() {
     return challanNumber;
 }

 public void setChallanNumber(String challanNumber) {
     this.challanNumber = challanNumber;
 }

    
    
   

}