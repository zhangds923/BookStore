package com.zds.web;

import com.zds.pojo.User;
import com.zds.service.UserService;
import com.zds.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.调用业务层代码处理业务逻辑
        User login = userService.login(new User(null, username, password, null));
        //3.登录成功和失败跳转
        if(login == null){
            //等于null说明登录失败，跳回登录页面
            req.getRequestDispatcher("/pages/user/login.html").forward(req,resp);

        } else{
            //登录成功，跳转到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);
        }
    }
}
