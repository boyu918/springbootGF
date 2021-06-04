package com.zby.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zby.manage.UserApi;
import com.zby.manage.dao.entity.User;
import com.zby.manage.dao.mapper.UserMapper;
import com.zby.manage.jwt.PassToken;
import com.zby.manage.model.UserIoc;
import com.zby.manage.model.UserJwt;
import com.zby.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zby
 * @time 2019/5/21 9:45
 */
@RestController
@RequestMapping("user")
public class UserController implements UserApi {

    @Autowired
    private UserIoc beanUser;

    @PostMapping("login")
    public Object login(UserJwt userForBase){
        Map<String,Object> map = new HashMap<>();
        if(userForBase==null){
            map.put("message","登录失败,用户不存在");
            return map;
        }else {
            if (!userForBase.getPassword().equals("123456")){
                map.put("message","登录失败,密码错误");
                return map;
            }else {
                String token = UserJwt.getToken(userForBase);
                map.put("token", token);
                map.put("user", userForBase);
                return map;
            }
        }
    }

    @PostMapping("rest")
    @PassToken
    public UserIoc testFirst(){
        System.out.println(beanUser);
        return beanUser;
    }

    @Autowired
    private UserMapper userMapper;
    @GetMapping("select/user")
//    @UserLoginToken
    public User selectUser(){
        User user = userMapper.selectByPrimaryKey(1L);
        return user;
    }
    @GetMapping("select/userByExample")
    public List<User> selectUserByExample(){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("age",33);
        PageHelper.startPage(1,1);
        List<User> user = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<User>(user);
        return pageInfo.getList();
    }

    @Autowired
    private UserService userService;
    @Value("${server.port}")
    private Integer port;
    @Override
    @PostMapping("hello")
    public String hello(){
//        userService.doServcie();
        return "生产者端口："+port;
    }

    @PostMapping("testfile")
    @ResponseBody
    public User testFile(@RequestPart MultipartFile file,@RequestPart User user){
        System.out.println(file.isEmpty());
        return user;
    }

}
