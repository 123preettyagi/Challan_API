package com.example.Challan_API.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Challan_API.dto.ChallanDTO;
import com.example.Challan_API.dto.ChallanPaymentDTO;
import com.example.Challan_API.dto.ChallanRequestDTO;
import com.example.Challan_API.dto.ReportDto;
import com.example.Challan_API.dto.SearchDTO;
import com.example.Challan_API.entity.Challan;

public interface ChallanService 
{
   
	ChallanDTO createChallan(ChallanRequestDTO challanRequest);
    ChallanDTO updateChallan(Long id, ChallanRequestDTO challanRequest);
    void deleteChallan(Long id);
    List<ChallanDTO> getAllChallans();
    ChallanDTO getChallanById(Long id);
    List<ChallanDTO> getChallansByUserId(Long userId);
    ChallanDTO payChallan(Long id, ChallanPaymentDTO paymentDTO);
    
    ReportDto getDailyReport();
    ReportDto getMonthlyReport();
    ReportDto getYearlyReport();
    ReportDto getReportByDateRange(String startDate, String endDate);
    
  
    
    ChallanDTO getChallanByChallanNumber(String challanNumber);

    
    List<ChallanDTO> getChallanHistoryByUser(Long userId);

    List<ChallanDTO> getChallanHistoryByVehicle(String vehicleNumber);

    List<ChallanDTO> getChallanHistoryByDate(
            LocalDateTime startDate,
            LocalDateTime endDate
    );
    
    
}