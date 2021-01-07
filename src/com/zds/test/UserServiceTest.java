package com.zds.test;

import com.zds.pojo.User;
import com.zds.service.UserService;
import com.zds.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();
    @Test
    public void registUser() {
        userService.registUser(new User(null,"zhangsan","666666","zhangsan@qq.com"));
        userService.registUser(new User(null,"abcsan","666666","abcsan@qq.com"));
    }

    @Test
    public void login() {
        String str = null;
        User user = userService.login(new User(null, "admin", "admin", "admin@testzds.com"));
        System.out.println(user);
        if(user == null){
            str = "登录失败";
            System.out.println(str);
            Assert.assertEquals("登录失败",str);
        } else{
            str = "登录成功";
            System.out.println(str);
            Assert.assertEquals("登录成功",str);
        }
    }

    @Test
    public void existUsername() {
        String str = null;
        if (userService.existUsername("admin")){
            str = "用户名已经存在";
            Assert.assertEquals("用户名已经存在",str);
            System.out.println(str);
        } else {
            str = "用户名不存在";
            Assert.assertEquals("用户名不存在",str);
            System.out.println(str);
        }
    }
}