package com.example.Challan_API.dto;

public class ReportDto {

    private Long totalChallans;
    private Double totalAmount;
    private Double collectedAmount;
    private Double pendingAmount;

    // No-argument constructor
    public ReportDto() {
    }

    // All-argument constructor
    public ReportDto(Long totalChallans, Double totalAmount, Double collectedAmount, Double pendingAmount) {
        this.totalChallans = totalChallans;
        this.totalAmount = totalAmount;
        this.collectedAmount = collectedAmount;
        this.pendingAmount = pendingAmount;
    }

    public Long getTotalChallans() {
        return totalChallans;
    }

    public void setTotalChallans(Long totalChallans) {
        this.totalChallans = totalChallans;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getCollectedAmount() {
        return collectedAmount;
    }

    public void setCollectedAmount(Double collectedAmount) {
        this.collectedAmount = collectedAmount;
    }

    public Double getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Double pendingAmount) {
        this.pendingAmount = pendingAmount;
    }
}
