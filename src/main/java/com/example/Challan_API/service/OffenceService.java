package com.example.Challan_API.service;

import java.util.List;

import com.example.Challan_API.dto.OffenceDto;
import com.example.Challan_API.dto.OffenceResponse;

public interface OffenceService {
    OffenceResponse addOffence(OffenceDto dto);
    List<OffenceResponse> getAllOffences();
    OffenceResponse getOffenceById(Long id);
    OffenceResponse updateOffence(Long id, OffenceDto dto);
    void deleteOffence(Long id);
}