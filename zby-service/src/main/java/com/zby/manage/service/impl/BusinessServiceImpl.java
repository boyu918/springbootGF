package com.zby.manage.service.impl;

import com.zby.manage.dao.entity.User;
import com.zby.manage.dao.mapper.UserMapper;
import com.zby.manage.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zby
 * @time 2019/7/8 16:09
 */
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private UserMapper userMapper;
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User doBusiness() {
        User user = new User();
        user.setName("xww");
        user.setAge(4);
        userMapper.insert(user);
        return user;
    }
}
