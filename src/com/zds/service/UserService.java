package com.zds.service;

import com.zds.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 登录
     * @param user
     * @return 返回null说明登录失败，有值就是成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return true表示用户名不可用，false可用
     */
    public boolean existUsername(String username);
}
