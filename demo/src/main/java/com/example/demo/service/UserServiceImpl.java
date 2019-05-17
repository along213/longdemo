package com.example.demo.service;

import com.example.demo.aop.service.LongLog;
import com.example.demo.aop.service.RequestLog;
import org.springframework.stereotype.Service;

@LongLog
@Service
public class UserServiceImpl implements UserService{

    @Override
    @RequestLog
    public String getUserInfo(String username) {
        return username;
    }
}
