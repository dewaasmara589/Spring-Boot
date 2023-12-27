package com.user_manager.services;

import com.user_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String fName, String lName, String email, String password){
        return userRepository.registerNewuser(fName, lName, email, password);
    }
}
