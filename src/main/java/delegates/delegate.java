package delegates;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public class delegate {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8081/Basic-Server";

    HttpHeaders headers = new HttpHeaders();
    String requestBody = "Hi Vishnu";
    HttpEntity<String> request = new HttpEntity<>(requestBody,headers);
    public Object restCallToServer(String urlMap){
        if(urlMap.equalsIgnoreCase("/test")){
            String fromServer = restTemplate.postForObject(url+urlMap,request, String.class);
            return fromServer;
        }
        return null;
    }
}
