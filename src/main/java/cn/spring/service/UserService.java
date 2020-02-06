package cn.spring.service;

import cn.spring.bean.User;
import cn.spring.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/5/2020
 */


public interface UserService {
    User findByUsername(String name);

    User findById(Integer id);
}
