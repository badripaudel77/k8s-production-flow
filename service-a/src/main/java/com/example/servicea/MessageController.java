package com.example.servicea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class MessageController {
    // use name from application.properties
    @Value("${spring.application.name}")
    private String serviceAName;

    @Value("${my.secret.password}")
    private String secretPassword;

    @GetMapping("/message")
    public String getMessage() {
        return "Hello from " + serviceAName + ", and this is super secret pw " + secretPassword + "!";
    }
}
