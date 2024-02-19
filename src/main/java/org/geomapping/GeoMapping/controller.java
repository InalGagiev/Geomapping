package org.geomapping.GeoMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.geomapping.GeoMapping.SpringConfig;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;

@RestController
@RequestMapping("/main")
@AllArgsConstructor
public class controller {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SpringConfig apiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @GetMapping("/controller")
    public ResponseEntity<?> getMainApi(@RequestParam String geocode) throws JsonProcessingException {

        String apiKey = apiConfig.getApiKey();
        String url = "https://geocode-maps.yandex.ru/1.x/?apikey=" + apiKey + "&format=json&geocode=" + geocode;

        String key = generateKey(geocode);

        if (redisTemplate.hasKey(key)) {
            return ResponseEntity.ok(redisTemplate.opsForValue().get(key));
        } else {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String json = response.getBody();

            redisTemplate.opsForValue().set(key, json);

            return response;
        }
    }
    private String generateKey(String geocode) {
        return "address:" + geocode;
    }
}