package com.cheng.confing;

import com.cheng.shiro.MyRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {


    /**
     * 设置页面能让那些角色和有那些权限的角色访问
     * @param defaultWebSecurityManager 是被注解进来的Myrealm对象
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean filterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        Map<String, String> map = new HashMap<>();

        map.put("/main","authc");
        map.put("/main.html","authc");

//        map.put("/main","roles[班主任]");
//        map.put("/main.html","roles[班主任]");

        shiroFilterFactoryBean.setLoginUrl("login.html");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

//        shiroFilterFactoryBean.setFilterChainDefinitions("/main");

        return shiroFilterFactoryBean;

    }


    /**
     * 将自己写的Myrealm方法注解进来。在Myrealm的类上写上注解@Component("myRealm")
     * 其中myRealm就是注解进来的关键
     * @param realm 是Myrealm对象
     * @return
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("myRealm") MyRealm realm){

        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 开启shiro注解
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(defaultWebSecurityManager);
        return  authorizationAttributeSourceAdvisor;
    }
}
