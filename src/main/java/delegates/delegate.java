package delegates;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

public class delegate {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    String baseUrl = "http://localhost:8080/Basic-Server";
    public Object restCallToServer(String urlMap, String userName){

        String requestBody = userName;
        HttpEntity<String> request = new HttpEntity<String>(requestBody,headers);
        if(urlMap.equalsIgnoreCase("/login")) {
             HashMap<String,String> user = restTemplate.postForObject(baseUrl + urlMap,requestBody, HashMap.class);
            return user;
        }else{
            HashMap<String, String> user = restTemplate.postForObject(baseUrl + urlMap, request,HashMap.class);
            return user;
        }
    }

    public HashMap<String, ArrayList<String>> getFoodItems(String restaurentName) {
        HttpEntity<String> request = new HttpEntity<String>(restaurentName,headers);
        HashMap<String, ArrayList<String>>  user = restTemplate.postForObject(baseUrl + "/foodItems",request, HashMap.class);
        return user;
    }
}
