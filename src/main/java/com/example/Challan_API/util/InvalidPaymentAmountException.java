package com.example.Challan_API.util;

public class InvalidPaymentAmountException extends RuntimeException 
{
    public InvalidPaymentAmountException(String message) {
        super(message);
    }
}