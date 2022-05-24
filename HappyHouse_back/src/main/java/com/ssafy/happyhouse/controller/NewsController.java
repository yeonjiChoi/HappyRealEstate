package com.ssafy.happyhouse.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.NewsDto;

@RestController
@RequestMapping("/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

	@GetMapping("/{searchval}")
	public ResponseEntity<List<NewsDto>> retrieveNews(@PathVariable String searchval) throws Exception {
		logger.debug("retrieveNews 호출 : {}", searchval);

		String clientId = "2F6_V51RKwFSmP88ZPG1"; //애플리케이션 클라이언트 아이디값"
		String clientSecret = "dp_FvKIX2P"; //애플리케이션 클라이언트 시크릿값"

		String text = null;
		try {
			text = URLEncoder.encode(searchval, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/news?query=" + text+ "&display=20&sort=sim";  // 20개씩 display, 유사도 순으로 sort


		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = get(apiURL,requestHeaders);

//		System.out.println(responseBody);
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(responseBody);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONArray item = (JSONArray) jsonObj.get("items");
		List<NewsDto> newsList = new ArrayList<NewsDto>();
		
		for(int i = 0; i < item.size(); i++) {
			NewsDto newsDto = new NewsDto(); 
			JSONObject object = (JSONObject)item.get(i);
			newsDto.setTitle((String) object.get("title"));
			newsDto.setOriginallink((String) object.get("originallink"));
			newsDto.setDescription((String) object.get("description"));
			newsDto.setPubDate((String) object.get("pubDate"));
			newsList.add(newsDto);
//			logger.debug("newsDto 생성 {}", newsDto);
		}
		
		return new ResponseEntity<List<NewsDto>>(newsList, HttpStatus.OK);
	}
	
    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
