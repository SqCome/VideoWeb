package com.lsq.search.contraller;



import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import com.lsq.search.utils.FileUtils.DownloadFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RequestMapping("/user")
@RestController
public class UserContraller {

    @Autowired
    private UserService userService;

    @Autowired
    private User userUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DownloadFile downloadFile;

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

    /**
     * 修改密码
     * @param info
     * @return
     */
    @RequestMapping("/newpassword")
    public ResEntity changePassword(@RequestBody Map<String,String> info){
         userUtil.setUsername(info.get("username"));
         userUtil.setPassword(info.get("password"));
         return userService.changePassword(userUtil,info.get("newpassword"));
    }


    /**
     * 注销用户
     * @param user
     * @return
     */
    @RequestMapping("/deleteuser")
    public ResEntity deleteUser(@RequestBody User user){
        return userService.deleteUser(user);
    }


    /**
     * 修改信息
     * @param user
     * @return
     */
    @RequestMapping("/changeinfo")
    public ResEntity changeInfo(@RequestBody User user){
        return null;
    }


    /**
     * 上传头像
     * @param file
     * @param userName
     * @return
     */
    @RequestMapping("/uploadIcon")
    public ResEntity uploadIcon(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "userName") String userName) {
        userUtil = userMapper.getUserByName(userName);
        return userService.uploadIcon(userUtil,file);
    }

//    @RequestMapping("/getIconUrl")
//    public ResEntity getIconUrl(@RequestBody User user){
//        userUtil = userMapper.getUserByName(user.getUsername());
//        return null;
//    }
}
