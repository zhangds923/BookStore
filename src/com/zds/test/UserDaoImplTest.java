package com.zds.test;

import com.zds.dao.UserDao;
import com.zds.dao.impl.UserDaoImpl;
import com.zds.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    private UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {

        User user = userDao.queryUserByUsername("admin123");
        System.out.println(user);
        if (user == null){
            System.out.println("用户名可用！");
        } else{
            System.out.println("用户名已经存在");
        }

    }

    @Test
    public void saveUser() {
        User user = new User(null,"admin123","123123","123@qq.com");
        int i = userDao.saveUser(user);
        System.out.println("影响行数：" + i);
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if (user == null){
            System.out.println("用户名或密码错误");
        } else{
            System.out.println("登录成功");
        }
    }
}