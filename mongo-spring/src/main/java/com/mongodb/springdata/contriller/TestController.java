package com.mongodb.springdata.contriller;

import com.mongodb.springdata.entity.User;
import com.mongodb.springdata.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : SH35856
 * @Description: TODO
 * @date: 2022/6/23 19:04
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Resource
    private UserRepository userRepository;

    @PostMapping("/save")
    public String saveUser(User user) {
        userRepository.saveUser(user);
        return "Success";
    }

    @GetMapping("/find")
    public String findUser(String name) {
        return userRepository.findUserByUserName(name).toString();
    }
}
