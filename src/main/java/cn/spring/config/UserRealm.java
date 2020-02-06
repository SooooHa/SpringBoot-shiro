package cn.spring.config;

import cn.spring.bean.User;
import cn.spring.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Security;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/4/2020
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;


    /**
     * 授权执行
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 虚假数据
        //info.addStringPermission("user:add");

        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        // 数据库查询
        User userRole = userService.findById(user.getId());

        info.addStringPermission(userRole.getPerms());

        return info;
    }


    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByUsername(token.getUsername());


        // //shiro底层会抛出UnKnowAccountException
        if (user==null){
            return null;
        }
        return new SimpleAuthenticationInfo(user,token.getPassword(),"");
    }
}
