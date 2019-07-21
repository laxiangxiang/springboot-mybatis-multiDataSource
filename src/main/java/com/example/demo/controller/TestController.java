package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMysql1Mapper;
import com.example.demo.mapper.UserMysql2Mapper;
import com.example.demo.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LXX on 2018/11/26.
 *
 * 也可以直接注入*.xml使用 ，此时需注意，优先使用被@Primary注解的SqlSessionFactory bean
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/mysql1/add")
    public void addToMysql1() throws Exception{
        userService.addToMysql1();
    }

    @RequestMapping("/mysql1/get")
    public UserEntity getFromMysql1(){
        return userService.getFromMysql1();
    }

    @RequestMapping("/mysql2/add")
    public void addToMysql2(){
        userService.addToMysql2();
    }

    @RequestMapping("/mysql2/get")
    public UserEntity getFromMysql2(){
        return userService.getFromMysql2();
    }

    /**
     * 这个方法执行会报错，因为默认的数据源是Oracle的oracleSqlSessionFactory。
     * 通过spring注入方式使用mapper class来操作数据库使用的都是默认的sqlSessionFactory
     * 由于MySQL与Oracle语句不通同所有以会报错。
     */
    @RequestMapping("/mapper/mysql1/add")
    public void addToMysql1WithMapper(){
        userService.addToMysql1WithMapper();
    }

    @RequestMapping("/oracle/add")
    public void addToOracle() throws Exception{
        userService.addToOracle();
    }

    @RequestMapping("/oracle/get")
    public UserEntity getFromOracle(){
        return userService.getFromOracle();
    }
}
