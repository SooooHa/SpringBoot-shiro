package cn.spring.dao;

import cn.spring.bean.User;
import cn.spring.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/5/2020
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired
    UserService userService;

    @Test
    public void  userTest(){
        User byUsername = userService.findByUsername("33");
        System.out.println(byUsername);
    }

    @Test
    public void findById(){
        User user = userService.findById(1);
        System.out.println(user);
    }
}
