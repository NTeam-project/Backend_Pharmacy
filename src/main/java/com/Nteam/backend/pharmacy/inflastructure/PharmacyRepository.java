package com.Nteam.backend.pharmacy.inflastructure;

import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {
}
