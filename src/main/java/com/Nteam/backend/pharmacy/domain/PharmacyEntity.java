package com.Nteam.backend.pharmacy.domain;

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
    private Long id;
    private String district;
    private String name;
    private String address;
    private String phone;
    private boolean english;
    private boolean chinese;
    private boolean japanese;
}
