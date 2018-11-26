package com.example.demo.controller;

import com.example.demo.entity.CutPart;
import com.example.demo.entity.RawMaterial;
import com.example.demo.mapper.CutPartMapper;
import com.example.demo.mapper.RawMaterialMapper;
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
@RequestMapping("/testApi")
public class TestController {

    @Autowired
    private CutPartMapper cutPartMapper;

    @Autowired
    private RawMaterialMapper rawMaterialMapper;

    @Autowired
    @Qualifier(value = "oracleSqlSessionFactory")
    private SqlSessionFactory oracleSqlSessionFactory;

    @Autowired
    @Qualifier(value = "mysqlSqlSessionFactory")
    private SqlSessionFactory mysqlSqlSessionFactory;

    @RequestMapping(value = "/getCutPart1",produces = MediaType.APPLICATION_JSON_VALUE)
    public CutPart getCutPart1(){
        return cutPartMapper.selectById(1l);
    }

    @RequestMapping(value = "/getCutPart2",produces = MediaType.APPLICATION_JSON_VALUE)
    public CutPart getCutPart2(){
        SqlSession sqlSession = mysqlSqlSessionFactory.openSession();
        CutPartMapper cutPartMapper1 = sqlSession.getMapper(CutPartMapper.class);
        return cutPartMapper1.selectById(1l);
    }

    @RequestMapping(value = "/getRawMaterial1",produces = MediaType.APPLICATION_JSON_VALUE)
    public RawMaterial getRawMaterial1(){
        return rawMaterialMapper.selectByPrimaryKey(1l);
    }

    @RequestMapping(value = "/getRawMaterial2",produces = MediaType.APPLICATION_JSON_VALUE)
    public RawMaterial getRawMaterial2(){
        SqlSession sqlSession = oracleSqlSessionFactory.openSession();
        RawMaterialMapper rawMaterialMapper = sqlSession.getMapper(RawMaterialMapper.class);
        return rawMaterialMapper.selectByPrimaryKey(881l);
    }
}
