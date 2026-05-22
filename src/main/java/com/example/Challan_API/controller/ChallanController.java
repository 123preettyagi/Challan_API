package com.example.Challan_API.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Challan_API.dto.ApiResponse;
import com.example.Challan_API.dto.ChallanDTO;
import com.example.Challan_API.dto.ChallanPaymentDTO;
import com.example.Challan_API.dto.ChallanRequestDTO;
import com.example.Challan_API.dto.DailyReportDTO;
import com.example.Challan_API.dto.OffenceDto;
import com.example.Challan_API.dto.OffenceResponse;
import com.example.Challan_API.dto.ReportDto;
import com.example.Challan_API.dto.SearchDTO;
import com.example.Challan_API.service.ChallanService;
import com.example.Challan_API.service.OffenceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/challans")
public class ChallanController
{
    
    @Autowired
    private ChallanService challanService;
    
    // POST /api/challans - Create challan (Admin only)
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChallanDTO> createChallan(@Valid @RequestBody ChallanRequestDTO challanRequest) 
    {
        ChallanDTO createdChallan = challanService.createChallan(challanRequest);
        return new ResponseEntity<>(createdChallan, HttpStatus.CREATED);
    }
    
    // PUT /api/challans/{id} - Update challan (Admin only)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChallanDTO> updateChallan(
            @PathVariable Long id,
            @Valid @RequestBody ChallanRequestDTO challanRequest) 
    {
        ChallanDTO updatedChallan = challanService.updateChallan(id, challanRequest);
        return ResponseEntity.ok(updatedChallan);
    }
    
    // DELETE /api/challans/{id} - Delete challan (Admin only)
    
   /* @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteChallan(@PathVariable Long id) {
        challanService.deleteChallan(id);
        return ResponseEntity.noContent().build();
    } */
    
   /* @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteChallan(@PathVariable Long id) {
        challanService.deleteChallan(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Challan deleted successfully");

        return ResponseEntity.ok(response);
    } */
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteChallan(@PathVariable Long id) {
        challanService.deleteChallan(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Challan deleted successfully");

        return ResponseEntity.ok(response);
    }



    
    
    // GET /api/challans - Get all challans (Admin only)
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> getAllChallans() {
        List<ChallanDTO> challans = challanService.getAllChallans();
        return ResponseEntity.ok(challans);
    }
    
    // GET /api/challans/{id} - Get challan by ID (User/Admin)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ChallanDTO> getChallanById(@PathVariable Long id) 
    {
        ChallanDTO challan = challanService.getChallanById(id);
        return ResponseEntity.ok(challan);
    }
    
    // GET /api/challans/user/{userId} - Get all challans by user (User/Admin)
    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> getChallansByUserId(@PathVariable Long userId) 
    {
        List<ChallanDTO> challans = challanService.getChallansByUserId(userId);
        return ResponseEntity.ok(challans);
    }
    
    // PUT /api/challans/{id}/pay - Pay challan (User only)
   /* @PutMapping("/{id}/pay")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ChallanDTO> payChallan(
            @PathVariable Long id,
            @Valid @RequestBody ChallanPaymentDTO paymentDTO) 
    {
        ChallanDTO paidChallan = challanService.payChallan(id, paymentDTO);
        return ResponseEntity.ok(paidChallan);
    }  */
    
    
  /*  @PutMapping("/{id}/pay")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<ChallanDTO>> payChallan(
            @PathVariable Long id,
            @Valid @RequestBody ChallanPaymentDTO paymentDTO) 
    {
    	

        ChallanDTO paidChallan = challanService.payChallan(id, paymentDTO);
        ApiResponse<ChallanDTO> response =
                new ApiResponse<>("Challan paid successfully", paidChallan);

        return ResponseEntity.ok(response);
        
    } */
    
    
    @PutMapping("/{id}/pay")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<ChallanDTO>> payChallan(
            @PathVariable Long id,
            @Valid @RequestBody ChallanPaymentDTO paymentDTO)
    {
        ChallanDTO paidChallan = challanService.payChallan(id, paymentDTO);
        ApiResponse<ChallanDTO> response =
                new ApiResponse<>("Challan paid successfully. Receipt sent via email", paidChallan);

        return ResponseEntity.ok(response);
    }


    @Autowired
    private OffenceService offenceService;

    // --- Offence APIs ---
    @PostMapping("/offences")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OffenceResponse> addOffence(@Valid @RequestBody OffenceDto dto) {
        return new ResponseEntity<>(offenceService.addOffence(dto), HttpStatus.CREATED);
    }

    @GetMapping("/offences")
    public ResponseEntity<List<OffenceResponse>> getAllOffences() {
        return ResponseEntity.ok(offenceService.getAllOffences());
    }

    @GetMapping("/offences/{id}")
    public ResponseEntity<OffenceResponse> getOffenceById(@PathVariable Long id) {
        return ResponseEntity.ok(offenceService.getOffenceById(id));
    }

    @PutMapping("/offences/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<OffenceResponse> updateOffence(@PathVariable Long id, @Valid @RequestBody OffenceDto dto) {
        return ResponseEntity.ok(offenceService.updateOffence(id, dto));
    }

    @DeleteMapping("/offences/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> deleteOffence(@PathVariable Long id) {
        offenceService.deleteOffence(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Offence deleted successfully");
        return ResponseEntity.ok(response);
    }

    
 // ---------- REPORT APIs ----------

    @GetMapping("/report/daily")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DailyReportDTO> getDailyReport() 
    {
    	DailyReportDTO report = new DailyReportDTO();
        report.setTotalChallans(5);             // example value
        report.setTotalAmount(1500.0);
        report.setCollectedAmount(1000.0);
        report.setPendingAmount(500.0);
        report.setReportDate(LocalDate.now().toString()); // new value
        report.setLocation("Delhi"); 
    	
       // return ResponseEntity.ok(challanService.getDailyReport());
        return ResponseEntity.ok(report);
    }

    @GetMapping("/report/monthly")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportDto> getMonthlyReport() {
        return ResponseEntity.ok(challanService.getMonthlyReport());
    }

    @GetMapping("/report/yearly")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportDto> getYearlyReport() {
        return ResponseEntity.ok(challanService.getYearlyReport());
    }

    @GetMapping("/report/custom")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ReportDto> getCustomReport(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return ResponseEntity.ok(challanService.getReportByDateRange(startDate, endDate));
    }

    
   /*  @PostMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> searchChallans(@RequestBody SearchDTO searchDTO) {
        List<ChallanDTO> result = challanService.searchChallans(searchDTO);
        return ResponseEntity.ok(result);
    }
*/
    
    
    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChallanDTO> searchByChallanNumber(
            @RequestParam String challanNumber) {

        ChallanDTO challan = challanService.getChallanByChallanNumber(challanNumber);
        return ResponseEntity.ok(challan);
    }

   

    @GetMapping("/history/user/{userId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> getChallanHistoryByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                challanService.getChallanHistoryByUser(userId)
        );
    }

    
    @GetMapping("/history/vehicle/{vehicleNumber}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> getChallanHistoryByVehicle(
            @PathVariable String vehicleNumber) {

        return ResponseEntity.ok(
                challanService.getChallanHistoryByVehicle(vehicleNumber)
        );
    }

    
    @GetMapping("/history/date")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ChallanDTO>> getChallanHistoryByDate(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        return ResponseEntity.ok(
            challanService.getChallanHistoryByDate(
                LocalDateTime.parse(startDate),
                LocalDateTime.parse(endDate)
            )
        );
    }


    
    
}