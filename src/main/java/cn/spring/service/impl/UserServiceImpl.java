package cn.spring.service.impl;

import cn.spring.bean.User;
import cn.spring.dao.UserDao;
import cn.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/5/2020
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserDao userDao;
    @Override
    public User findByUsername(String name) {
        return userDao.findByUsername(name);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> user
                = userDao.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        return null;
    }


}
