package com.example.Challan_API.dto;

public class OffenceDto {
    private String name;
    private Double fineAmount;
    private String description;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getFineAmount() { return fineAmount; }
    public void setFineAmount(Double fineAmount) { this.fineAmount = fineAmount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}