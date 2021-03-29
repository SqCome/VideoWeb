package com.lsq.search.contraller;



import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserContraller {

    @Resource(name = "userService")
    private UserService userService;

    @Resource
    private User user;

    @RequestMapping("/query")
    public List<User> getUser(){
        return userService.getUserList();
    }



    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("/singup")
    @ResponseBody
    public ResEntity insertUser(@RequestBody User user){
            return userService.insertUserService(user);
    }


    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResEntity signIn(@RequestBody User user){
        System.out.println(user.getUsername()+"请求登录");
        return userService.signIn(user);
    }

    @RequestMapping("/newpassword")
    public ResEntity changePassword(@RequestBody Map<String,String> info){
         user.setUsername(info.get("username"));
         user.setPassword(info.get("password"));
         return userService.changePassword(user,info.get("newpassword"));
    }

    @RequestMapping("/deleteuser")
    public ResEntity deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }

    @RequestMapping("/changeinfo")
    public ResEntity changeInfo(@RequestBody User user){
        return null;
    }
}
