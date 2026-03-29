package com.example.serviceb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class CombinedController {
    private  RestTemplate restTemplate = new RestTemplate();

    @Value("${servicea.url}")
    private String serviceAUrl;

    @Value("${spring.application.name}")
    private String serviceBName;


    @GetMapping("/message")
    public String getCombined() {
        // print the url to verify it's correct
        System.out.println("Service B is calling Service A at: " + serviceAUrl);
        String messageFromA = restTemplate.getForObject(serviceAUrl, String.class);
        return serviceBName + " received: " + messageFromA;
    }
}
