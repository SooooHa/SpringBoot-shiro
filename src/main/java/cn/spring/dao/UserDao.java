package cn.spring.dao;

import cn.spring.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: 57251180@qq.com
 * Date: 2/5/2020
 */

public interface UserDao extends JpaRepository<User,Integer> {

    User findByUsername(String name);


    Optional<User> findById(Integer id);

}
