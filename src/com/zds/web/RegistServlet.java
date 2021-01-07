package com.zds.web;

import com.zds.pojo.User;
import com.zds.service.UserService;
import com.zds.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String code = req.getParameter("code");
        String email = req.getParameter("email");
        //2 检查验证码是否正确，暂时写死，要求验证码是abcde
        if ("abcde".equalsIgnoreCase(code)){
            //3 检查用户名是否可用
            if(userService.existUsername(username)){
                //用户名不可用
                System.out.println("用户名：" + username + "不可用");
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            }
            else{
                //4 保存到数据库中
                userService.registUser(new User(null,username,password,email));
                //跳转到注册成功页面
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);
            }

        }else {
            //验证码不正确
            System.out.println("验证码：" + code + "错误");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
        }

    }
}
