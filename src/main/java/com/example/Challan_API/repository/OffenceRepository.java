package com.example.Challan_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Challan_API.entity.Offence;

@Repository
public interface OffenceRepository extends JpaRepository<Offence, Long> {
    // You can add custom query methods here if needed
}