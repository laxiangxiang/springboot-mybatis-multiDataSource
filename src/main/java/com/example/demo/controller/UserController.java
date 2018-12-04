package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.datasourceone.User1Mapper;
import com.example.demo.mapper.datasourcetwo.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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

    ///////////////////事务管理测试//////////////////////////////////////

    /**
     * 自动提交、回滚事务
     * 如果不使用事务该记录会被删除
     */
    @RequestMapping("/transactionTest")
    @Transactional(transactionManager = "test2TransactionManager")
    public void transactionTest(){
        user2Mapper.delete(1);
        UserEntity userEntity = user2Mapper.getOne(1);
        userEntity.getPassword();
    }

    @Autowired
    @Qualifier("test2TransactionManager")
    private PlatformTransactionManager platformTransactionManager;

    /**
     * 手动提交、回滚事务
     */
    @RequestMapping("/transactionTest2")
    public void transactionTest2(){
        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = platformTransactionManager.getTransaction(definition);
        try{
            user2Mapper.delete(1);
            UserEntity userEntity = user2Mapper.getOne(1);
            userEntity.getPassword();
        }catch (Exception e){
            platformTransactionManager.rollback(status);
            throw e;
        }
        platformTransactionManager.commit(status);
    }
}
