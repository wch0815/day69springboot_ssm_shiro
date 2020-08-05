package com.cheng.service.impl;

import com.cheng.dao.IUserDao;
import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.User;
import com.cheng.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Override
    public User login(User u) {
        return userDao.login(u);
    }

    @Override
    public List<Role> getAllRoleByUsername(String username) {
        return userDao.getAllRoleByUsername(username);
    }

    @Override
    public List<Permission> getAllPermissionByUsername(String username) {
        return userDao.getAllPermissionByUsername(username);
    }
}
