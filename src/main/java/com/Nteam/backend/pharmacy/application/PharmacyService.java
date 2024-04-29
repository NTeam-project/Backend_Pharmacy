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

    public PharmacyService(PharmacyRepository pharmacyRepository){ //의존성주입
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<PharmacyDTO> getPharmacyList() { //전체조회
        return pharmacyRepository.findAll().stream().map(this::transform).collect(Collectors.toList());
    }

    public PharmacyDTO getPharmacy(Long id) { //하나 조회
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
                    .build();
        }
        return null;
    }

    private PharmacyDTO transform(PharmacyEntity pharmacyEntity) { //변경폼
        return PharmacyDTO.builder()
                .id(pharmacyEntity.getId())
                .district(pharmacyEntity.getDistrict())
                .name(pharmacyEntity.getName())
                .address(pharmacyEntity.getAddress())
                .phone(pharmacyEntity.getPhone())
                .english(pharmacyEntity.isEnglish())
                .chinese(pharmacyEntity.isChinese())
                .japanese(pharmacyEntity.isJapanese())
                .build();
    }

//
//    public void generateCoordinate(String apiKey, String url, String address) {
//        ResponseEntity<CoordinateDTO> responseEntity = requestCoordiate(apiKey, url, address);
//
//    }
//
//    private ResponseEntity<CoordinateDTO> requestCoordiate(String apiKey, String url, String address) throws UnsupportedEncodingException {
//        address = URLEncoder.encode(address, "UTF-8");
//        URI uri = UriComponentsBuilder
//                .fromUriString(url)
//                .path(address)
//                .queryParam("url", address)
//                .encode()
//                .build()
//                .toUri();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("");
//
//    }
}
