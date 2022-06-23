package com.mongodb.springdata.repository;

import com.mongodb.springdata.entity.User;

/**
 * @author : SH35856
 * @Description: TODO
 * @date: 2022/6/23 18:59
 */
public interface UserRepository {
    public void saveUser(User user);

    public User findUserByUserName(String userName);

    public long updateUser(User user);

    public void deleteUserById(Long id);
}
