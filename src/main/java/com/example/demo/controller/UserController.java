package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.datasourceone.User1Mapper;
import com.example.demo.mapper.datasourcetwo.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by LXX on 2018/11/28.
 */
@RestController
@RequestMapping("/userApi")
public class UserController {

    @Autowired
    private User1Mapper user1Mapper;

    @Autowired
    private User2Mapper user2Mapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        List<UserEntity> users=user1Mapper.getAll();
        return users;
    }

    @RequestMapping("/getUser")
    public UserEntity getUser(int id) {
        UserEntity user=user2Mapper.getOne(id);
        return user;
    }

    @RequestMapping("/add")
    public void save(UserEntity user) {
        user2Mapper.insert(user);
    }

    @RequestMapping(value="update")
    public void update(UserEntity user) {
        user2Mapper.update(user);
    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        user1Mapper.delete(id);
    }

}
