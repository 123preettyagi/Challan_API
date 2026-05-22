package com.example.Challan_API.util;

public class ChallanAlreadyPaidException extends RuntimeException 
{
    public ChallanAlreadyPaidException(String message) {
        super(message);
    }
}