package com.zds.service.impl;

import com.zds.dao.UserDao;
import com.zds.dao.impl.UserDaoImpl;
import com.zds.pojo.User;
import com.zds.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        User user1 = userDao.queryUserByUsernameAndPassword(user.getUserName(), user.getPassWord());
        return user1;
    }

    @Override
    public boolean existUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if(user == null){
            //说明没查到，表示用户名可用
            return false;
        }
        else{
            return true;
        }
    }
}
