package com.user_manager.controllers;

import com.user_manager.models.UpdatePassword;
import com.user_manager.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

// TODO 18 ~ Make Controller to Update Password
@RestController
@RequestMapping("/api")
public class UpdatePasswordController {
    @Autowired
    UserService userService;

    @PostMapping("/user/update")
    public ResponseEntity updatePassword(@RequestBody UpdatePassword updatePassword){
        String hashedPassword = userService.checkUserPasswordByEmail(updatePassword.getEmail());

        System.out.println("Password DB: " + hashedPassword +", Password Input : " + updatePassword.getOldPassword());

        // Validate User Password
        if (!BCrypt.checkpw(updatePassword.getOldPassword(), hashedPassword)){
            return new ResponseEntity("Incorrect Old Password", HttpStatus.BAD_REQUEST);
        }

        // Encrypt / Hash Password:
        String hashedNewPassword = BCrypt.hashpw(updatePassword.getNewPassword(), BCrypt.gensalt());

        int responseUpdatePassword = userService.setUpdatePassword(updatePassword.getEmail(), hashedNewPassword);
        if (responseUpdatePassword != 1){
            return new ResponseEntity("Failed to Update Password", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Success to Update Password", HttpStatus.OK);
    }
}

// TODO 19 ~ Controller to Update Password Request
// Link Test API : http://localhost:9080/api/user/update
// Body - Raw
//{
//    "email" : "test@gmail.com",
//    "oldPassword" : "test123",
//    "newPassword" : "test321"
//}
