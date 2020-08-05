package com.cheng.service;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.User;

import java.util.List;

public interface IUserService {

    User login(User u);

    List<Role> getAllRoleByUsername(String username);

    List<Permission> getAllPermissionByUsername(String username);
}
