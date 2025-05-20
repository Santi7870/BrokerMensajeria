package com.santi.walkietalkie;

import com.santi.walkietalkie.controller.MessageRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TestClientRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        MessageRequest request = new MessageRequest();
        request.alias = "Santi";
        request.message = "Hola desde CommandLineRunner";

        String url = "http://localhost:8080/api/send";

        String response = restTemplate.postForObject(url, request, String.class);
        System.out.println("Respuesta: " + response);
    }
}
