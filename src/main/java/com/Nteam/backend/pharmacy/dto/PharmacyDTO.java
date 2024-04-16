package com.Nteam.backend.pharmacy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PharmacyDTO {
    private Long id;
    private String district;
    private String name;
    private String address;
    private String phone;
    private boolean english;
    private boolean chinese;
    private boolean japanese;
    private boolean otherLanguages;
    private String note;

}
