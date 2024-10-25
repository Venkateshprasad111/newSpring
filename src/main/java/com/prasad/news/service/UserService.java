package com.prasad.news.service;

import com.prasad.news.dao.UserRepo;
import com.prasad.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepo userRepo;







    public String upsertUser(User user) {
        try {
            userRepo.save(user);
            return "Success";
        }catch (DataIntegrityViolationException e){
//            System.out.println(user.getUserName());
            throw new RuntimeException("Already entry for the user "+user.getUserName());
        }
    }
}
