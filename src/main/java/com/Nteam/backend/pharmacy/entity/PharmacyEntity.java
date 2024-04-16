package com.Nteam.backend.pharmacy.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private boolean otherLanguages;
    private String note;

}
