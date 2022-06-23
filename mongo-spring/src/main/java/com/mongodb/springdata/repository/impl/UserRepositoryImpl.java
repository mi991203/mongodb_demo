package com.mongodb.springdata.repository.impl;

import com.mongodb.springdata.entity.User;
import com.mongodb.springdata.repository.UserRepository;
import org.springframework.data.geo.Circle;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author : SH35856
 * @Description: TODO
 * @date: 2022/6/23 19:00
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(User user) {
        mongoTemplate.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        final Query query = new Query(Criteria.where("name").is(userName));
        final User user = mongoTemplate.findOne(query, User.class);
        return user;
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }

    @Override
    public void deleteUserById(Long id) {

    }
}
