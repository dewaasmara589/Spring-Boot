package com.user_manager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO 4 ~ Make Controller
@RestController
@RequestMapping("/api")
public class UserApiController {
    @GetMapping("/test")
    public String testEndpoint(){
        return "Test Endpoint is working";
    }
}

// TODO 5 ~ Test Run (Maven -> user-management -> Plugins -> spring-boot -> spring-boot:run)
// TODO 6 ~ Check in Postman (GET : 127.0.0.1:9080/api/test)