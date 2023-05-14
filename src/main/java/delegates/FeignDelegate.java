package delegates;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;

@FeignClient(name="server", url = "http://localhost:8080/Basic-Server")
@SpringBootApplication
public interface FeignDelegate {

    @PostMapping("/login")
    ResponseEntity<HashMap<String, String>> postTest(@RequestBody String request);
}
