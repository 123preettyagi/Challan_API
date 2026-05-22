package com.example.Challan_API.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Challan_API.dto.ChallanDTO;
import com.example.Challan_API.dto.ChallanPaymentDTO;
import com.example.Challan_API.dto.ChallanRequestDTO;
import com.example.Challan_API.dto.ReportDto;
import com.example.Challan_API.dto.SearchDTO;
import com.example.Challan_API.entity.Challan;
import com.example.Challan_API.entity.User;
import com.example.Challan_API.repository.ChallanMapper;
import com.example.Challan_API.repository.ChallanRepository;
import com.example.Challan_API.repository.UserRepository;
import com.example.Challan_API.util.PdfGenerator;
import com.example.Challan_API.util.ResourceNotFoundException;


@Service
public class ChallanServiceImpl implements ChallanService 
{
    
    @Autowired
    private ChallanRepository challanRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    

    
    @Override
    public ChallanDTO createChallan(ChallanRequestDTO challanRequest) {
        User user = userRepository.findById(challanRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Challan challan = new Challan();
       // challan.setViolationType(challanRequest.getViolationType());
        challan.setVehicleNumber(challanRequest.getVehicleNumber());
        challan.setLocation(challanRequest.getLocation());
        challan.setAmount(challanRequest.getAmount());
        challan.setViolationDate(challanRequest.getViolationDate());
        challan.setUser(user);
        challan.setDescription(challanRequest.getDescription());
        challan.setStatus("PENDING");
        
        Challan savedChallan = challanRepository.save(challan);
        return convertToDTO(savedChallan);
    }
    
    @Override
    public ChallanDTO updateChallan(Long id, ChallanRequestDTO challanRequest) {
        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challan not found"));
        
        User user = userRepository.findById(challanRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        //challan.setViolationType(challanRequest.getViolationType());
        challan.setVehicleNumber(challanRequest.getVehicleNumber());
        challan.setLocation(challanRequest.getLocation());
        challan.setAmount(challanRequest.getAmount());
        challan.setViolationDate(challanRequest.getViolationDate());
        challan.setUser(user);
        challan.setDescription(challanRequest.getDescription());
        challan.setUpdatedAt(LocalDateTime.now());
        
        Challan updatedChallan = challanRepository.save(challan);
        return convertToDTO(updatedChallan);
    }
    
    @Override
    public void deleteChallan(Long id) {
        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challan not found"));
        challanRepository.delete(challan);
    }
    
    @Override
    public List<ChallanDTO> getAllChallans() {
        List<Challan> challans = challanRepository.findAll();
        return challans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    public ChallanDTO getChallanById(Long id) {
        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challan not found"));
        return convertToDTO(challan);
    }
    
    @Override
    public List<ChallanDTO> getChallansByUserId(Long userId) {
        List<Challan> challans = challanRepository.findByUserId(userId);
        return challans.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
   /* @Override
    public ChallanDTO payChallan(Long id, ChallanPaymentDTO paymentDTO) {
        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Challan not found"));
        
        if (!challan.getStatus().equals("PENDING")) {
            throw new RuntimeException("Challan is not pending for payment");
        }
        
        if (paymentDTO.getAmountPaid() < challan.getAmount()) {
            throw new RuntimeException("Payment amount is less than challan amount");
        }
        
        challan.setStatus("PAID");
        challan.setPaymentDate(LocalDateTime.now());
        challan.setUpdatedAt(LocalDateTime.now());
        
        Challan paidChallan = challanRepository.save(challan);
        return convertToDTO(paidChallan);
    } */
    
    
 /*   @Autowired
    private EmailService emailService;

    @Override
    public ChallanDTO payChallan(Long id, ChallanPaymentDTO paymentDTO) {

        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challan not found"));

        challan.setStatus("PAID");
        challan.setPaymentDate(LocalDateTime.now());
        challanRepository.save(challan);

        ChallanDTO dto = mapToDTO(challan);

        // ----- Generate PDF -----
        byte[] pdfBytes = PdfGenerator.generateChallanPDF(dto);

        // ----- Send Email -----
        String email = challan.getUser().getEmail();  // Make sure User entity has email
        emailService.sendChallanReceipt(email, pdfBytes, "challan_receipt_" + id + ".pdf");

        return dto;
    } */
    
    
    @Autowired
    private EmailService emailService;

    @Override
    public ChallanDTO payChallan(Long id, ChallanPaymentDTO paymentDTO) 
    {

        // 1. Fetch the challan from DB
        Challan challan = challanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Challan not found"));

        // 2. Validate if challan is already paid
        if ("PAID".equalsIgnoreCase(challan.getStatus())) {
            throw new RuntimeException("Challan is already paid");
        }

        // 3. Validate payment amount
        //if (paymentDTO.getAmountPaid() != challan.getAmount()) 
        	// FIXED AMOUNT COMPARISON
        if (Double.compare(paymentDTO.getAmountPaid(), challan.getAmount()) != 0)
        {
            // Throwing exception with a meaningful message
            throw new RuntimeException("Payment amount must match the challan amount: " + challan.getAmount());
        }

        // 4. Update challan as paid
        challan.setStatus("PAID");
        challan.setPaymentDate(LocalDateTime.now());
        challan.setUpdatedAt(LocalDateTime.now());
        challanRepository.save(challan);

        // 5. Map to DTO
        ChallanDTO dto = mapToDTO(challan);

        // 6. Generate PDF
        byte[] pdfBytes = PdfGenerator.generateChallanPDF(dto);

        // 7. Send email with PDF attachment
        String email = challan.getUser().getEmail();
        emailService.sendChallanReceipt(email, pdfBytes, "challan_receipt_" + id + ".pdf");

        return dto;
    }



    
    private ChallanDTO convertToDTO(Challan challan) 
    {
        ChallanDTO dto = new ChallanDTO();
        dto.setId(challan.getId());
       // dto.setViolationType(challan.getViolationType());
        dto.setVehicleNumber(challan.getVehicleNumber());
        dto.setLocation(challan.getLocation());
        dto.setAmount(challan.getAmount());
        dto.setViolationDate(challan.getViolationDate());
        dto.setPaymentDate(challan.getPaymentDate());
        dto.setStatus(challan.getStatus());
        dto.setUserId(challan.getUser().getId());
        dto.setUserName(challan.getUser().getFullName());
        dto.setDescription(challan.getDescription());
        dto.setCreatedAt(challan.getCreatedAt());
        dto.setUpdatedAt(challan.getUpdatedAt());
        return dto;
    }
    
    private ChallanDTO mapToDTO(Challan challan) 
    {
        ChallanDTO dto = new ChallanDTO();
        dto.setId(challan.getId());
       // dto.setViolationType(challan.getViolationType());
        dto.setVehicleNumber(challan.getVehicleNumber());
        dto.setLocation(challan.getLocation());
        dto.setAmount(challan.getAmount());
        dto.setViolationDate(challan.getViolationDate());
        dto.setPaymentDate(challan.getPaymentDate());
        dto.setStatus(challan.getStatus());
        dto.setUserId(challan.getUser().getId());
        dto.setUserName(challan.getUser().getFullName());
        dto.setDescription(challan.getDescription());
        dto.setCreatedAt(challan.getCreatedAt());
        dto.setUpdatedAt(challan.getUpdatedAt());
        return dto;
    }

    
    
    @Override
    public ReportDto getDailyReport() {
        LocalDate today = LocalDate.now();
        return challanRepository.getReportByDate(today.atStartOfDay(), today.plusDays(1).atStartOfDay());
    }

    @Override
    public ReportDto getMonthlyReport() {
        LocalDate firstDay = LocalDate.now().withDayOfMonth(1);
        LocalDate lastDay = LocalDate.now().plusMonths(1).withDayOfMonth(1);

        return challanRepository.getReportByDate(firstDay.atStartOfDay(), lastDay.atStartOfDay());
    }

    @Override
    public ReportDto getYearlyReport() {
        LocalDate firstDay = LocalDate.now().withDayOfYear(1);
        LocalDate lastDay = LocalDate.now().plusYears(1).withDayOfYear(1);

        return challanRepository.getReportByDate(firstDay.atStartOfDay(), lastDay.atStartOfDay());
    }

    @Override
    public ChallanDTO getChallanByChallanNumber(String challanNumber) {
        Challan challan = challanRepository.findByChallanNumber(challanNumber);

        if (challan == null) {
            throw new RuntimeException("Challan not found with number: " + challanNumber);
        }

        return convertToDTO(challan);
    }

	@Override
	public ReportDto getReportByDateRange(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return null;
	}



	// Challan History
	
	// @Autowired
	  //  private ChallanRepository challanRepository;

	    
	@Autowired
    private ChallanMapper challanMapper;

   /* @Override
    public List<ChallanDTO> getChallanHistoryByUser(Long userId) {
        return challanRepository.findByUser_Id(userId)
                .stream()
                .map(challanMapper::toDTO)
                .toList();
    } */ 
	
	@Override
	public List<ChallanDTO> getChallanHistoryByUser(Long userId) {
	    List<Challan> challans = challanRepository.findByUser_Id(userId);
	    return challans.stream()
	                   .map(challanMapper::toDTO)
	                   .collect(Collectors.toList());
	}


    @Override
    public List<ChallanDTO> getChallanHistoryByVehicle(String vehicleNumber) {
        return challanRepository.findByVehicleNumber(vehicleNumber)
                .stream()
                .map(challanMapper::toDTO)
                .toList();
    }

    @Override
    public List<ChallanDTO> getChallanHistoryByDate(
            LocalDateTime startDate,
            LocalDateTime endDate) {

        return challanRepository
                .findByViolationDateBetween(startDate, endDate)
                .stream()
                .map(challanMapper::toDTO)
                .toList();
    }

} 

