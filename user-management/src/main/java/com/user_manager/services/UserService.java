package com.user_manager.services;

import com.user_manager.models.User;
import com.user_manager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String fName, String lName, String email, String password){
        return userRepository.registerNewuser(fName, lName, email, password);
    }
    // End of Register New User Services Method

    public List<String> checkUserEmail(String email){
        return userRepository.checkUserEmail(email);
    }
    // End of Check User Email Services Method

    public String checkUserPasswordByEmail(String email){
        return userRepository.checkUserPasswordByEmail(email);
    }
    // End of Check User Password Services Method

    public User getUserDetailByEmail(String email){
        return userRepository.GetUserDetailsByEmail(email);
    }
    // End of Check User Details By Email
}

// TODO 12 ~ Service to check email, password Login, and return detail user if response success