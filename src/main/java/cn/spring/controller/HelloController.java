package cn.spring.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/4/2020
 * @author Administrator
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","张三");
        return "/hello";
    }


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "/loginD";
    }

    @RequestMapping("/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("/update")
    public String hai(){
        return "user/update";
    }

    @RequestMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    @RequestMapping("/login")
    public String login(String username,String password,Model model){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);


        try {
            subject.login(token);
            // 登录成功
            return "redirect:/hello";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
