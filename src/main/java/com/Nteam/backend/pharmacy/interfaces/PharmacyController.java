package com.Nteam.backend.pharmacy.interfaces;

import com.Nteam.backend.pharmacy.application.PharmacyService;
import com.Nteam.backend.pharmacy.inflastructure.datatool.PharmacyDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    private PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService){
        this.pharmacyService = pharmacyService;
    }

    @GetMapping()
    public List<PharmacyDTO> getAllPharmacies(){
        return pharmacyService.getPharmacyList();
    }
    @GetMapping("/{id}")
    public PharmacyDTO getPharmacy(@PathVariable(value = "id") Long id){
        System.out.print(id);
        return pharmacyService.getPharmacy(id);

    }
}
