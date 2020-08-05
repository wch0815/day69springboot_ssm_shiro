package com.cheng.dao;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserDao {

    User login(User u);

    List<Role> getAllRoleByUsername(String username);

    List<Permission> getAllPermissionByUsername(String username);

}
