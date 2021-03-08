package com.mohen.service;

import com.mohen.domain.User;

import java.util.List;

/**
 * @author MH19
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> getAll();

    /**
     * 保存用户信息
     * @param list 保存数据
     */
    void saveBatch(List<User> list);
}
