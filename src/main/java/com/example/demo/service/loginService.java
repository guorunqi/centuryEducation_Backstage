package com.example.demo.service;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import org.springframework.stereotype.Service;

/**
 * Created by guorunqi on 2019/1/14.
 */
@Service
public class LoginService {
    private UserMapper UserMapper;
    public boolean verifyLogin(User user){
        return true;
    }
}
