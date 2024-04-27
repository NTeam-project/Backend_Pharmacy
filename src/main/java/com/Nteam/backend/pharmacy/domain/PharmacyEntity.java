package com.Nteam.backend.pharmacy.domain;

import com.Nteam.backend.pharmacy.inflastructure.datatool.PharmacyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pharmacy_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String district;
    private String name;
    private String address;
    private String phone;
    private boolean english;
    private boolean chinese;
    private boolean japanese;
    private String otherLanguages;

    public static PharmacyEntity toEntity(PharmacyDTO pharmacyDTO){
        PharmacyEntity pharmacyEntity = new PharmacyEntity();
        pharmacyEntity.setDistrict(pharmacyDTO.getDistrict());
        pharmacyEntity.setName(pharmacyDTO.getName());
        pharmacyEntity.setAddress(pharmacyDTO.getAddress());
        pharmacyEntity.setPhone(pharmacyDTO.getPhone());
        pharmacyEntity.setEnglish(pharmacyEntity.isEnglish());
        pharmacyEntity.setChinese(pharmacyEntity.isChinese());
        pharmacyEntity.setJapanese(pharmacyEntity.isJapanese());
        pharmacyEntity.setOtherLanguages(pharmacyEntity.getOtherLanguages());
        return pharmacyEntity;
    }

}
