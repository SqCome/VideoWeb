package com.lsq.search.serviceimpl;


import com.lsq.search.entity.ResEntity;
import com.lsq.search.entity.User;
import com.lsq.search.mapper.UserMapper;
import com.lsq.search.service.FileStorageService;
import com.lsq.search.service.UserService;
import com.lsq.search.utils.ResCode;
import com.lsq.search.utils.SignUpCheck;
import com.lsq.search.utils.FileUtils.FileStorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Path fileStorageLocation;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private User checkUser;

    @Autowired
    private FileStorageService fileStorageService;


    @Autowired
    public UserServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                                    .toAbsolutePath().normalize();
    }


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
            Path myLocation = this.fileStorageLocation.resolve(Paths.get(user.getUsername()));
            Path myIcon = myLocation.resolve(Paths.get("Icon"));
            Path myVideo = myLocation.resolve(Paths.get("Video"));
            user.setTargetLocation(myLocation.toString());
            Files.createDirectories(myIcon);
            Files.createDirectories(myVideo);
            if (userMapper.getUserByName(user.getUsername()) != null ){
                resEntity.setCode(ResCode.WARN.getCode());
                resEntity.setMessage(ResCode.WARN.getMsg());
                resEntity.setData(SignUpCheck.NAME_REPEAT);
            }else {
                userMapper.insertUser(user);
                resEntity.setCode(ResCode.SUCCESS.getCode());
                resEntity.setMessage(ResCode.SUCCESS.getMsg());
                resEntity.setData(SignUpCheck.SUCCESS);
            }
            return resEntity;
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData(e);
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
                    resEntity.setData(SignUpCheck.SUCCESS);
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
                    resEntity.setData(SignUpCheck.SUCCESS);
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
                resEntity.setData(SignUpCheck.SUCCESS);
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


    /**
     * 修改信息
     * @param user
     * @return
     */
    @Override
    public ResEntity changeInfo(User user) {
        ResEntity resEntity = new ResEntity();
        try {
            userMapper.updateUser(user);
            resEntity.setCode(ResCode.SUCCESS.getCode());
            resEntity.setMessage(ResCode.SUCCESS.getMsg());
            resEntity.setData("修改成功！");
            return resEntity;
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData("修改失败！");
            System.out.println(e);
            return resEntity;
        }



    }

    /**
     * 上传头像
     * @param user
     * @return
     */
    @Override
    public ResEntity uploadIcon(User user, MultipartFile file) {
        ResEntity resEntity = new ResEntity();
        try {
            checkUser.setUsername(user.getUsername());
            String iconUrl = fileStorageService.storeFile(file,user,"Icon");
            checkUser.setIcon(iconUrl);
            this.changeInfo(checkUser);
            resEntity.setCode(ResCode.SUCCESS.getCode());
            resEntity.setMessage(ResCode.SUCCESS.getMsg());
            resEntity.setData("上传成功！");
        }catch (Exception e){
            resEntity.setCode(ResCode.ERROR.getCode());
            resEntity.setMessage(ResCode.ERROR.getMsg());
            resEntity.setData("上传失败！"+e);
        }
        return resEntity;
    }

    /**
     * 得到头像的存储地址
     * @param id
     * @return
     */
    @Override
    public String getIconUrl(int id) {
        return userMapper.getUserById(id).getIcon();
    }

    @Override
    public ResEntity getUserVideoList(User user) {
        return null;
    }
}
