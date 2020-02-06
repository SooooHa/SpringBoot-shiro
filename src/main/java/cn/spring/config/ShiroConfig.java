package cn.spring.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/4/2020
 */

@Configuration
public class ShiroConfig {


    /**
     * 创建ShiroFileterFactoryBean
     * @param securityManager
     * @return
     */
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 添加Shiro内置过滤器

        /**
         * 内置过滤器，实现权限相关的拦截器
         */
        Map<String,String> filterMap = new LinkedHashMap<>();


        /**
         * anno 无需登录
         * authc 必须认证
         * user 使用remember的功能
         * perms 得到资源权限
         * role 该资源必须得到角色权限
         */
        // 不拦截的部分
        filterMap.put("/hello","anon");
        filterMap.put("/toLogin", "anon");
        filterMap.put("/login","anon");

        // 授权过滤
        filterMap.put("/add","perms[user:add]");
        filterMap.put("/update","perms[user:update]");

        // 全部拦截
        filterMap.put("/*","authc");


        // 设置未授权的提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

        // 拦截的登录页面
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     *
     * @param realm
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 关联realm
        securityManager.setRealm(realm);
        return securityManager;
    }

    /**
     * 创建Realm
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }

    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
