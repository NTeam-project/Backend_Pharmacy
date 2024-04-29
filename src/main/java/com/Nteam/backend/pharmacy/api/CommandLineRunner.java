package com.Nteam.backend.pharmacy.api;

import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import com.Nteam.backend.pharmacy.inflastructure.PharmacyRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.IOException;
@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    private PharmacyRepository pharmacyRepository;

    public CommandLineRunner( PharmacyRepository pharmacyRepository) { //의존성
        this.pharmacyRepository = pharmacyRepository;
    }

    @Override
    public void run(String... args){
        ObjectMapper mapper = new ObjectMapper();

        try {
            ClassPathResource resource = new ClassPathResource("pharmacyList.json");
            JsonNode root = mapper.readTree(resource.getInputStream()); // JSON 데이터를 JsonNode로 읽음

            for (JsonNode node : root) { //돌면서 저정
                PharmacyEntity pharmacy = new PharmacyEntity();
                pharmacy.setId(node.get("연번").asLong());
                pharmacy.setDistrict(node.get("자치구").asText());
                pharmacy.setName(node.get("약국이름").asText());
                pharmacy.setAddress(node.get("주소 (도로명)").asText());
                pharmacy.setPhone(node.get("전화번호").asText());
                pharmacy.setEnglish(node.has("영어") && "○".equals(node.get("영어").asText()));
                pharmacy.setChinese(node.has("중국어") && "○".equals(node.get("중국어").asText()));
                pharmacy.setJapanese(node.has("일본어") && "○".equals(node.get("일본어").asText()));
                pharmacyRepository.save(pharmacy);
            }
        }catch (IOException e){
            System.out.print(e);
        }
    }
}
