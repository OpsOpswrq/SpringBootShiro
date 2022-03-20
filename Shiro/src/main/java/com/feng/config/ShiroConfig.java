package com.feng.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.awt.image.ImageWatched;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        Map<String, String> LinkedHashMap = new LinkedHashMap<>();
        //授权，正常情况下，没有授权会跳转到为授权页面
        LinkedHashMap.put("/user/addUser","perms[add]");
        LinkedHashMap.put("/user/updateUser","perms[update]");
        LinkedHashMap.put("/user/*","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(LinkedHashMap);
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setUnauthorizedUrl("/noauth");
        return shiroFilterFactoryBean;
    }
    @Bean("SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") UseRealm useRealm){
        DefaultWebSecurityManager SecurityManager = new DefaultWebSecurityManager();
        SecurityManager.setRealm(useRealm);
        return SecurityManager;
    }
    @Bean(name="realm")
    public UseRealm useRealm(){
        return new UseRealm();
    }
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
