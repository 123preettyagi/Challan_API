package com.example.Challan_API.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Challan_API.dto.OffenceDto;
import com.example.Challan_API.dto.OffenceResponse;
import com.example.Challan_API.entity.Offence;
import com.example.Challan_API.repository.OffenceRepository;

@Service
public class OffenceServiceImpl implements OffenceService {

    @Autowired
    private OffenceRepository offenceRepository;

    @Override
    public OffenceResponse addOffence(OffenceDto dto) {
        Offence offence = new Offence(dto.getName(), dto.getFineAmount(), dto.getDescription());
        offenceRepository.save(offence);
        return mapToResponse(offence);
    }

    @Override
    public List<OffenceResponse> getAllOffences() {
        return offenceRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OffenceResponse getOffenceById(Long id) {
        Offence offence = offenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offence not found"));
        return mapToResponse(offence);
    }

    @Override
    public OffenceResponse updateOffence(Long id, OffenceDto dto) {
        Offence offence = offenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offence not found"));
        offence.setName(dto.getName());
        offence.setFineAmount(dto.getFineAmount());
        offence.setDescription(dto.getDescription());
        offenceRepository.save(offence);
        return mapToResponse(offence);
    }

    @Override
    public void deleteOffence(Long id) {
        Offence offence = offenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Offence not found"));
        offenceRepository.delete(offence);
    }

    private OffenceResponse mapToResponse(Offence offence) {
        OffenceResponse response = new OffenceResponse();
        response.setId(offence.getId());
        response.setName(offence.getName());
        response.setFineAmount(offence.getFineAmount());
        response.setDescription(offence.getDescription());
        return response;
    }
}