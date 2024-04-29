package com.Nteam.backend.pharmacy.interfaces;
import com.Nteam.backend.pharmacy.application.PharmacyService;
import com.Nteam.backend.pharmacy.inflastructure.datatool.PharmacyDTO;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService){
        this.pharmacyService = pharmacyService;
    }

    @GetMapping()
    public List<PharmacyDTO> getAllPharmacies(){
        return pharmacyService.getPharmacyList();

    }
    @GetMapping("/{id}")
    public PharmacyDTO getPharmacy(@PathVariable(value = "id") Long id){
        System.out.print(id);
        return pharmacyService.getPharmacy(id);

    }

    @RequestMapping(value = "/map", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public String getKakaoApiFromAddress(@RequestParam("address") String roadFullAddr) {
        String apiKey = "6697ce651492e186db0ea6d0c9dc850a";
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String jsonString = null;

        try {
            // 1. URL 인코딩 - URL로 사용할 수 없는 문자를 '%XX'의 형태로 변환
            // ex) 파라미터 address를 한글로 사용하기 떄문에 인코딩을 해주자
            roadFullAddr = URLEncoder.encode(roadFullAddr, "UTF-8");

            // 2. 요청 url을 만들기
            String addr = apiUrl + "?query=" + roadFullAddr;

            // 3. URL 객체 생성
            URL url = new URL(addr);

            // 4. URL Connection 객체 생성
            URLConnection conn = url.openConnection();

            // 5. 헤더값 설정해주기
            conn.setRequestProperty("Authorization", "KakaoAK " + apiKey);

            // 6. StringBuffer에 값을 넣고 String 형태로 변환하고 jsonString을 return
            BufferedReader rd = null;
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));


            StringBuffer docJson = new StringBuffer();
            String line;

            while ((line=rd.readLine()) != null) {
                docJson.append(line);
            }

            jsonString = docJson.toString();
            rd.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
