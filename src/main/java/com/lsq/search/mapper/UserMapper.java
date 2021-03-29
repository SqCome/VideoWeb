package com.lsq.search.mapper;

import com.lsq.search.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getUserList();
    int insertUser(User user);
    User getUserByName(@Param("username") String username);
    int deleteUser(User user);
    int changePwd(@Param("username") String username,@Param("newpassword")String newpassword);
    int updateUser(User user);
}
