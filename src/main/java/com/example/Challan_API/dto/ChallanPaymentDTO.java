package com.example.Challan_API.dto;

public class ChallanPaymentDTO {
    private Double amountPaid;
    private String paymentMethod;
    private String transactionId;
    
    public ChallanPaymentDTO() {}
    
    // Getters and Setters
    public Double getAmountPaid() {
        return amountPaid;
    }
    
    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}