package com.zds.dao.impl;

import com.zds.dao.UserDao;
import com.zds.pojo.User;

public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        User user = queryForOne(User.class, sql, username);
        return user;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        int update = update(sql, user.getUserName(), user.getPassWord(), user.getEmail());
        return update;
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        User user = queryForOne(User.class, sql, username,password);
        return user;
    }
}
