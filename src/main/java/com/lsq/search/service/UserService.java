package com.lsq.search.service;

import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Classname UserService
 * @Description TODO
 * @Date 2021/3/6 21:38
 * @Auth LSQ
 */
public interface UserService {
    public List<User> getUserList();
    public ResEntity insertUserService(User user);
    public ResEntity signIn(User user);
    public ResEntity changePassword(User user,String newpassword);
    public ResEntity deleteUser(User user);
    public ResEntity changeInfo(User user);
    public ResEntity uploadIcon(User user, MultipartFile file);
    public Resource downloadIcon(User user, String fileName);
}
