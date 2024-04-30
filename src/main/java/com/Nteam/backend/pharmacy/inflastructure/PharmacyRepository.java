package com.Nteam.backend.pharmacy.inflastructure;

import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<PharmacyEntity, Long> {
    // 자치구 이름으로 약국 정보 조회
//    List<PharmacyEntity> findByPharGu(String pharGu);

}
