package com.Nteam.backend.pharmacy.api;
import com.Nteam.backend.pharmacy.domain.PharmacyEntity;
import com.Nteam.backend.pharmacy.inflastructure.PharmacyRepository;
import com.Nteam.backend.pharmacy.inflastructure.datatool.ResponseDTO;
import com.Nteam.backend.pharmacy.inflastructure.datatool.address.RootDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {

    @Value("${pharmacy.api.key}")
    private String API_KEY;

    private PharmacyRepository pharmacyRepository;

    public CommandLineRunner( PharmacyRepository pharmacyRepository) { //의존성
        this.pharmacyRepository = pharmacyRepository;
    }
    @Override
    public void run(String... args) {
        ObjectMapper mapper = new ObjectMapper();
        String address;
        double longtitude;
        double latitude;
        List<String> arr = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("pharmacyList.json");
            JsonNode root = mapper.readTree(resource.getInputStream()); // JSON 데이터를 JsonNode로 읽음

            for (JsonNode node : root) { //돌면서 저장
                PharmacyEntity pharmacy = new PharmacyEntity();
                pharmacy.setPhar_id(node.get("연번").asLong());
                pharmacy.setPhar_gu(node.get("자치구").asText());
                pharmacy.setPhar_name(node.get("약국이름").asText());
                pharmacy.setAddress(node.get("주소 (도로명)").asText());
                address = node.get("주소 (도로명)").asText();
                arr.add(address);
                longtitude = generateCoordinate(address).getLongitude();
                latitude = generateCoordinate(address).getLatitude();
                pharmacy.setLongitude(longtitude);
                pharmacy.setLatitude(latitude);
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
    public ResponseDTO generateCoordinate(String address) throws UnsupportedEncodingException {
        ResponseEntity<RootDto> responseEntity = requestCoordinate(API_KEY, address); //요청 api, 주소

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            System.out.print(responseEntity.getBody());
            RootDto rootDto = responseEntity.getBody();
            if(rootDto.isDocumentsEmpty()){
                ResponseDTO responseDTO = new ResponseDTO(37.5, 37.5);
                return responseDTO;
            }
            ResponseDTO responseDTO = new ResponseDTO(rootDto.getDocuments().get(0).getX(), rootDto.getDocuments().get(0).getY()); //추가
            return responseDTO;
            }

        return null;
    }

    private ResponseEntity<RootDto> requestCoordinate(String apiKey, String address) throws UnsupportedEncodingException {
        String encodedAddress = URLEncoder.encode(address, "UTF-8");

        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        URI uri = UriComponentsBuilder //url생성
                .fromUriString(apiUrl)
                .queryParam("query", address)
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders(); //헤더 생성
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "KakaoAK " + apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<RootDto> response = restTemplate.exchange(uri, HttpMethod.GET, entity, RootDto.class); //url + 헤더 + get요청 후 Dto변환
        return response;
    }
}