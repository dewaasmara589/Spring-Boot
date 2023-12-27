package com.user_manager.controllers;

import com.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// TODO 8 ~ Make Controller Register and Set Required Parameter
@RestController
@RequestMapping("/api")
public class RegisterApiController {
    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password){

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()){
            return new ResponseEntity<>("Please Complete all Fields", HttpStatus.BAD_REQUEST);
        }

        // Encrypt / Hash Password:
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        /// Register New User:
        /// result back 1 or 0
        int result = userService.registerNewUserServiceMethod(firstName, lastName, email, hashed_password);

        if (result != 1){
            return new ResponseEntity<>("Failed to Register User", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
    }
}
