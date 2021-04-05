package com.lsq.search;

import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.mapper.VideoMapper;
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

    @Resource
    private VideoMapper videoMapper;

    @Test
    void contextLoads() {
        user = userMapper.getUserById(13);
        User my = videoMapper.getMyVideos(user);
        System.out.println(my);
    }

}
