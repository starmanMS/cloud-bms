package com.ms.service.impl;

import com.ms.entity.User;
import com.ms.mapper.UserMapper;
import com.ms.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper mapper;

    @Override
    public User getUserById(int uid) {
        return mapper.getUserById(uid);
    }
}