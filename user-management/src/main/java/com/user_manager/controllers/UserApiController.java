package com.user_manager.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserApiController {
    @GetMapping("/test")
    public String testEndpoint(){
        return "Test Endpoint is working";
    }
}
