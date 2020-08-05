package com.cheng.shiro;

import com.cheng.pojo.Permission;
import com.cheng.pojo.Role;
import com.cheng.pojo.User;
import com.cheng.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("myRealm")
public class MyRealm extends AuthorizingRealm {

    @Resource
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //  可以获取用户名
        String username = getAvailablePrincipal(principals).toString();

        List<Role> roles = userService.getAllRoleByUsername(username);

        for (Role r : roles) {
            info.addRole(r.getRname());
            System.out.println(r);
        }

        List<Permission> perms = userService.getAllPermissionByUsername(username);

        for(Permission p : perms){
            info.addStringPermission(p.getPname());
            System.out.println(p);
        }

//        System.out.println(username + "================");

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken tk = (UsernamePasswordToken) token;
        String username = tk.getUsername();

        String password = new String(tk.getPassword());

        String md5Hash = new Md5Hash(password,"wang").toString();
        User u = new User();
        u.setUsername(username);
        u.setPassword(md5Hash);

        System.out.println(u);
        User user = userService.login(u);

        if (user != null)
            return new SimpleAuthenticationInfo(username,password,getName());
        return null;
    }
}
