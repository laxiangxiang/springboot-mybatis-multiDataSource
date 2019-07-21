package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMysql1Mapper;
import com.example.demo.mapper.UserMysql2Mapper;
import com.example.demo.mapper.UserOracleMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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
    @Qualifier(value = "mysql1SqlSessionFactory")
    private SqlSessionFactory mysql1SqlSessionFactory;

    @Autowired
    @Qualifier(value = "mysql2SqlSessionFactory")
    private SqlSessionFactory mysql2SqlSessionFactory;

    @Autowired
    private UserOracleMapper userOracleMapper;

    @Autowired
    private UserMysql1Mapper userMysql1Mapper;

    @RequestMapping("/mysql1/add")
    public void addToMysql1(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("lxx");
        userEntity.setUserNo("001");
        SqlSession sqlSession = mysql1SqlSessionFactory.openSession();
        UserMysql1Mapper userMysql1Mapper = sqlSession.getMapper(UserMysql1Mapper.class);
        userMysql1Mapper.insert(userEntity);
        sqlSession.commit();
        sqlSession.close();
    }

    @RequestMapping("/mysql1/get")
    public UserEntity getFromMysql1(){
        SqlSession sqlSession = mysql1SqlSessionFactory.openSession();
        UserMysql1Mapper userMysql1Mapper = sqlSession.getMapper(UserMysql1Mapper.class);
        UserEntity userEntity = userMysql1Mapper.getOne(1);
        sqlSession.close();
        return userEntity;
    }

    @RequestMapping("/mysql2/add")
    public void addToMysql2(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("lxx");
        userEntity.setUserNo("001");
        SqlSession sqlSession = mysql2SqlSessionFactory.openSession();
        UserMysql2Mapper userMysql2Mapper = sqlSession.getMapper(UserMysql2Mapper.class);
        userMysql2Mapper.insert(userEntity);
        sqlSession.commit();
        sqlSession.close();
    }

    @RequestMapping("/mysql2/get")
    public UserEntity getFromMysql2(){
        SqlSession sqlSession = mysql2SqlSessionFactory.openSession();
        UserMysql2Mapper userMysql2Mapper = sqlSession.getMapper(UserMysql2Mapper.class);
        UserEntity userEntity = userMysql2Mapper.getOne(7);
        sqlSession.close();
        return userEntity;
    }

    /**
     * 这个方法执行会报错，因为默认的数据源是Oracle的oracleSqlSessionFactory。
     * 通过spring注入方式使用mapper class来操作数据库使用的都是默认的sqlSessionFactory
     * 由于MySQL与Oracle语句不通同所有以会报错。
     */
    @RequestMapping("/mapper/mysql1/add")
    public void addToMysql1WithMapper(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("lxx-m1");
        userEntity.setUserNo("001");
        userMysql1Mapper.insert(userEntity);
    }

    @RequestMapping("/oracle/add")
    public void addToOracle(){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("123456");
        userEntity.setUserName("lxx");
        userEntity.setUserNo("001");
        userOracleMapper.insert(userEntity);
    }

    @RequestMapping("/oracle/get")
    public UserEntity getFromOracle(){
        return userOracleMapper.getOne(1);
    }
}
