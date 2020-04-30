package com.cskaoyan.config;

import com.cskaoyan.realm.CustomRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Author: Li Qing
 * @Create: 2020/4/23 16:27
 * @Version: 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //认证失败后的重定向
        shiroFilterFactoryBean.setLoginUrl("/unAuthc");
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //过滤免登录权限请求,过滤迭代有序
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/auth/login", "anon");
        filterMap.put("unAuthc", "anon");
        filterMap.put("/index", "anon");
        //权限可以采用声明式，shiro支持
        filterMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /*
     * 声明式鉴权 注解需要的组件
     * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    //shiro核心组件 SecurityManager
    @Bean
    public DefaultWebSecurityManager securityManager(CustomRealm customRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
        defaultWebSecurityManager.setSessionManager(webSessionManager());
        //defaultWebSecurityManager.setSessionManager(webSessionManager);
        return defaultWebSecurityManager;
    }

    @Bean
    public DefaultWebSessionManager webSessionManager() {
        return new CustomSessionManager();
    }
    //
    //@DependsOn("lifecycleBeanPostProcessor")
    //@Bean
    //public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    //    DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
    //    creator.setProxyTargetClass(true);
    //    return creator;
    //}
    //
    //@Bean
    //public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    //    return new LifecycleBeanPostProcessor();
    //}
}
