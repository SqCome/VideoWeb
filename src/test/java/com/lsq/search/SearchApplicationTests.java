package com.lsq.search;

import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import com.lsq.search.serviceimpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SearchApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private User user;

    @Test
    void contextLoads() {
        user.setUsername("beauty");
        user.setEmail("POP@lsq.com");
        int i = userMapper.updateUser(user);
        System.out.println(i);
        System.out.println(user);
    }

}
