package com.jeskey.bookmark.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NaverApiService {
    String clientId = "WiqLrZreg705VmdQgdeV";
    String clientSecret = "TGr0sR8Le2";

    int display = 10;

    public List<Map> bookSearch(String title, int page){

        int start = 1;

        if(page > 1){
            start = (page - 1) * display + 1;
        }

        String apiURL = "https://openapi.naver.com/v1/search/book?query=" + title + "&start=" + start;

        Map<String, String> requestHeaders = new HashMap<>();

        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL,requestHeaders);

        List<Map> searchList = getList(responseBody);
        return searchList;
    }

    //String 타입의 Json text를 Json으로 변환해 List 객체로 만든다.
    private List<Map> getList(String text){

        JSONArray jsonArray = new JSONObject(text).getJSONArray("items");

        List<Map> list = new ArrayList<>();

        for(int i = 0; i<jsonArray.length(); i++){
            JSONObject object = jsonArray.getJSONObject(i);
            Map<String, String> jsonMap = new HashMap<>();

            jsonMap.put("isbn", object.getString("isbn"));
            jsonMap.put("title",object.getString("title"));
            jsonMap.put("author",object.getString("author"));
            jsonMap.put("image",object.getString("image"));
            jsonMap.put("pubdate", object.getString("pubdate"));
            jsonMap.put("publisher", object.getString("publisher"));
            jsonMap.put("description", object.getString("description"));

            list.add(jsonMap);
        }

        return list;
    }

    private String get(String apiUrl, Map<String, String> requestHeaders){
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

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
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
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
}
