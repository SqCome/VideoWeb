package com.lsq.search.serviceimpl;


import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.UserService;
import com.lsq.search.utils.ResCode;
import com.lsq.search.utils.SignUpCheck;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private User checkUser;


    @Override
    public List<User> getUserList() {

        return userMapper.getUserList();
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public ResEntity insertUserService(User user){
        ResEntity resEntity = new ResEntity();
        try {
            if (userMapper.getUserByName(user.getUsername()) != null ){
                resEntity.setCode(ResCode.WARN.getCode());
                resEntity.setMessage(ResCode.WARN.getMsg());
                resEntity.setData(SignUpCheck.NAME_REPEAT);
            }else {
                userMapper.insertUser(user);
                resEntity.setCode(ResCode.SUCCESS.getCode());
                resEntity.setMessage(ResCode.SUCCESS.getMsg());
                resEntity.setData(SignUpCheck.SUCCESSS);
            }
            return resEntity;
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            return resEntity;
        }

    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public ResEntity signIn(User user){
        ResEntity resEntity = new ResEntity();
        try {
            checkUser = userMapper.getUserByName(user.getUsername());   //查询
            if (checkUser != null){
                if (checkUser.getPassword().equals(user.getPassword())){  //密码正确
                    resEntity.setCode(ResCode.SUCCESS.getCode());
                    resEntity.setMessage(ResCode.SUCCESS.getMsg());
                    resEntity.setData(SignUpCheck.SUCCESSS);
                }else {  //密码不正确
                    resEntity.setCode(ResCode.WARN.getCode());
                    resEntity.setMessage(ResCode.WARN.getMsg());
                    resEntity.setData(SignUpCheck.PASSWORD_ERROR);
                }
            }else {  //查询用户不存在
                resEntity.setCode(ResCode.WARN.getCode());
                resEntity.setMessage(ResCode.WARN.getMsg());
                resEntity.setData(SignUpCheck.NAME_NOT_EXIT);
            }
            return resEntity;
        }catch (Exception e){  //未知异常
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            return resEntity;
        }
    }

    /**
     * 修改密码
     * @param user
     * @param newpassword
     * @return
     */
    @Override
    public ResEntity changePassword(User user,String newpassword){
        ResEntity resEntity = new ResEntity();
        try{
            checkUser = userMapper.getUserByName(user.getUsername());
            if (checkUser != null){
                if (checkUser.getPassword().equals(user.getPassword())){  //旧密码正确
                    userMapper.changePwd(user.getUsername(),newpassword);
                    resEntity.setCode(ResCode.SUCCESS.getCode());
                    resEntity.setMessage(ResCode.SUCCESS.getMsg());
                    resEntity.setData(SignUpCheck.SUCCESSS);
                }else {  //旧密码不正确
                    resEntity.setCode(ResCode.WARN.getCode());
                    resEntity.setMessage(ResCode.WARN.getMsg());
                    resEntity.setData(SignUpCheck.PASSWORD_ERROR);
                }
            }else{  //其他错误
                resEntity.setCode(ResCode.ERROR.getCode());
                resEntity.setMessage(ResCode.ERROR.getMsg());
                resEntity.setData(SignUpCheck.ERROR);
            }
            return resEntity;
        }catch (Exception e){   //未知错误
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData(SignUpCheck.ERROR);
            return resEntity;
        }
    }

    /**
     * 用户注销
     * @param user
     * @return
     */
    @Override
    public ResEntity deleteUser(User user){
        ResEntity resEntity = new ResEntity();
        try {
            checkUser = userMapper.getUserByName(user.getUsername());
            if (checkUser.getPassword().equals(user.getPassword())){  //核验密码通过
                userMapper.deleteUser(user);
                resEntity.setCode(ResCode.SUCCESS.getCode());
                resEntity.setMessage(ResCode.SUCCESS.getMsg());
                resEntity.setData(SignUpCheck.SUCCESSS);
            }else{   //核验密码不通过
                resEntity.setCode(ResCode.WARN.getCode());
                resEntity.setMessage(ResCode.WARN.getMsg());
                resEntity.setData(SignUpCheck.PASSWORD_ERROR);
            }
            return resEntity;
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData(SignUpCheck.ERROR);
            return resEntity;
        }
    }



    @Override
    public ResEntity changeInfo(User user) {

        return null;
    }
}
