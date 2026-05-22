package com.example.Challan_API.repository;


import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Challan_API.dto.ReportDto;
import com.example.Challan_API.dto.SearchDTO;
import com.example.Challan_API.entity.Challan;

@Repository
public interface ChallanRepository extends JpaRepository<Challan, Long> 
//public interface ChallanRepository extends JpaRepository<Challan, Long>, JpaSpecificationExecutor<Challan>
{
    List<Challan> findByUserId(Long userId);
    List<Challan> findByStatus(String status);
    

    @Query("SELECT new com.example.Challan_API.dto.ReportDto(" +
    	       "COUNT(c), SUM(c.totalAmount), SUM(c.paidAmount), SUM(c.totalAmount - c.paidAmount)) " +
    	       "FROM Challan c WHERE c.createdAt BETWEEN :start AND :end")
    	ReportDto getReportByDate(@Param("start") LocalDateTime start,
    	                          @Param("end") LocalDateTime end);
    
        
    Challan findByChallanNumber(String challanNumber);
    
   // Challan History
    
    
    List<Challan> findByUser_Id(Long userId);

    List<Challan> findByVehicleNumber(String vehicleNumber);

    List<Challan> findByViolationDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );
    
 
}
