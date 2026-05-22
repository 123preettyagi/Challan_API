package com.example.Challan_API.repository;


import org.apache.ibatis.annotations.Mapper;

import com.example.Challan_API.dto.ChallanDTO;
import com.example.Challan_API.entity.Challan;


//@Mapper(componentModel = "spring")
@Mapper
public interface ChallanMapper 
{
    
	//ChallanDTO toDTO(Challan challan);
	//Challan toEntity(ChallanDTO challanDTO);
	
	
	default ChallanDTO toDTO(Challan challan) {
        if (challan == null) return null;
        ChallanDTO dto = new ChallanDTO();
        dto.setId(challan.getId());
        dto.setChallanNumber(challan.getChallanNumber());
        dto.setVehicleNumber(challan.getVehicleNumber());
        dto.setLocation(challan.getLocation());
        dto.setAmount(challan.getAmount());
        dto.setStatus(challan.getStatus());
        dto.setViolationDate(challan.getViolationDate());
        dto.setPaymentDate(challan.getPaymentDate());
        return dto;
    }

    default Challan toEntity(ChallanDTO dto) {
        if (dto == null) return null;
        Challan challan = new Challan();
        challan.setId(dto.getId());
        challan.setChallanNumber(dto.getChallanNumber());
        challan.setVehicleNumber(dto.getVehicleNumber());
        challan.setLocation(dto.getLocation());
        challan.setAmount(dto.getAmount());
        challan.setStatus(dto.getStatus());
        challan.setViolationDate(dto.getViolationDate());
        challan.setPaymentDate(dto.getPaymentDate());
        return challan;
    }
	
	
}