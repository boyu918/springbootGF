package com.zby.manage.service.impl;

import com.zby.manage.dao.entity.User;
import com.zby.manage.dao.mapper.UserMapper;
import com.zby.manage.service.BusinessService;
import com.zby.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author zby
 * @time 2019/7/8 16:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BusinessService businessService;
    @Override
    @Transactional
    public User doServcie() {
        User user = new User();
        user.setName("xss");
        user.setAge(5);
//        userMapper.insert(user);
//        businessService.doBusiness();
//        int a = 1/0;
//        System.out.println(a);
        return user;
    }
    @Override
    public String doTest(int i){
        if (i == 3){
            i = 55;
        }
        return i+"";
    }
}
