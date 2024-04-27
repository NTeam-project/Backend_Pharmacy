package com.Nteam.backend.pharmacy.inflastructure.datatool;

import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PharmacyDTO {
    private Long id;
    private String district;
    private String name;
    private String address;
    private String phone;
    private boolean english;
    private boolean chinese;
    private boolean japanese;
    private String otherLanguages;


    static public PharmacyDTO toPharmacyDTO(PharmacyEntity pharmacyEntity){
        return new PharmacyDTO(
                pharmacyEntity.getId(),
                pharmacyEntity.getDistrict(),
                pharmacyEntity.getName(),
                pharmacyEntity.getAddress(),
                pharmacyEntity.getPhone(),
                pharmacyEntity.isEnglish(),
                pharmacyEntity.isChinese(),
                pharmacyEntity.isJapanese(),
                pharmacyEntity.getOtherLanguages()
        );
    }

}
