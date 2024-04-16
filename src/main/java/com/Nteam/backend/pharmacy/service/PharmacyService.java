package com.Nteam.backend.pharmacy.service;

import com.Nteam.backend.pharmacy.dto.PharmacyDTO;
import com.Nteam.backend.pharmacy.repository.PharmacyRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {
    private PharmacyRepository pharmacyRepository;



}
