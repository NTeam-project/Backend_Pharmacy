package com.Nteam.backend.pharmacy.controller;

import com.Nteam.backend.pharmacy.dto.PharmacyDTO;
import com.Nteam.backend.pharmacy.service.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pharmacies")
public class PharmacyController {

    private PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyDTO> getAllPharmacies() {
        return pharmacyService.getAllPharmacies();
    }
}
