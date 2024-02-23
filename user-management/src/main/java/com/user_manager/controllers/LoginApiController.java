package com.user_manager.controllers;

import com.user_manager.models.Login;
import com.user_manager.models.User;
import com.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login){

        // Get User Email
        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        // Check if Email is Empty
        if (userEmail.isEmpty() || userEmail == null){
            return new ResponseEntity("Email does not exist", HttpStatus.NOT_FOUND);
        }
        // End of Check if Email is Empty;

        // Get Hashed User Password
        String hashedPassword = userService.checkUserPasswordByEmail(login.getEmail());

        // Validate User Password
        if (!BCrypt.checkpw(login.getPassword(), hashedPassword)){
            return new ResponseEntity("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }

        // Set User Object
        User user = userService.getUserDetailByEmail(login.getEmail());
        return new ResponseEntity(user, HttpStatus.OK);
    }

}

// TODO 14 ~ Controller to Login Request
// Link Test API : http://localhost:9080/api/user/login
// Body - Raw
//{
//   "email" : "coba@gmail.com",
//   "password" : "coba123"
//}
