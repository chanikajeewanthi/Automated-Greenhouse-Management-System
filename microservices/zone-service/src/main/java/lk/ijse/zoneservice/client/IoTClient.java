package lk.ijse.zoneservice.client;

import lk.ijse.zoneservice.dto.DeviceResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class IoTClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String BASE_URL = "http://104.211.95.241:8080/api/devices";

    public DeviceResponseDTO registerDevice(String name, String zoneId, String token) {

        Map<String, String> body = Map.of(
                "name", name,
                "zoneId", zoneId
        );

        var headers = new org.springframework.http.HttpHeaders();
        headers.setBearerAuth(token);

        var request = new org.springframework.http.HttpEntity<>(body, headers);

        return restTemplate.postForObject(BASE_URL, request, DeviceResponseDTO.class);
    }
}