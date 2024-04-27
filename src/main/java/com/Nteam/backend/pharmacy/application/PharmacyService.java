package com.Nteam.backend.pharmacy.application;

import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import com.Nteam.backend.pharmacy.inflastructure.PharmacyRepository;
import com.Nteam.backend.pharmacy.inflastructure.datatool.PharmacyDTO;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PharmacyService {
    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(PharmacyRepository pharmacyRepository){
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<PharmacyDTO> getPharmacyList() {
        return pharmacyRepository.findAll().stream().map(this::transform).collect(Collectors.toList());
    }

    private PharmacyDTO transform(PharmacyEntity pharmacyEntity) {
        return PharmacyDTO.builder()
                .id(pharmacyEntity.getId())
                .district(pharmacyEntity.getDistrict())
                .name(pharmacyEntity.getName())
                .address(pharmacyEntity.getAddress())
                .phone(pharmacyEntity.getPhone())
                .english(pharmacyEntity.isEnglish())
                .chinese(pharmacyEntity.isChinese())
                .japanese(pharmacyEntity.isJapanese())
                .otherLanguages(pharmacyEntity.getOtherLanguages())
                .build();
    }

    public PharmacyDTO getPharmacy(Long id) {
        Optional<PharmacyEntity> optionalPharmacy = pharmacyRepository.findById(id);
        System.out.print(id);
        if(optionalPharmacy.isPresent()){
            PharmacyEntity pharmacyEntity = optionalPharmacy.get();
            return PharmacyDTO.builder()
                    .id(pharmacyEntity.getId())
                    .district(pharmacyEntity.getDistrict())
                    .name(pharmacyEntity.getName())
                    .address(pharmacyEntity.getAddress())
                    .phone(pharmacyEntity.getPhone())
                    .english(pharmacyEntity.isEnglish())
                    .chinese(pharmacyEntity.isChinese())
                    .japanese(pharmacyEntity.isJapanese())
                    .otherLanguages(pharmacyEntity.getOtherLanguages())
                    .build();
        }
        return null;

    }


//    public List<PharmacyDTO> getPharmacyList() {
//        return pharmacyRepository.findAll().stream().collect(Collectors.toList());
//    }


}
