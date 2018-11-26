package com.example.demo.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by LXX on 2018/11/26.
 */
@Configuration
public class MybatisConfig {

    private static final String resource = "mybatis-config.xml";

    private static final String mybatisProperties = "mybatis.properties";

    private static Properties properties = null;

    static {
        try {
            properties = Resources.getResourceAsProperties(mybatisProperties);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Bean(name = "mysqlSqlSessionFactory")
    public SqlSessionFactory getMysqlSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql",properties);
        return sqlSessionFactory;
    }

    @Bean(name = "oracleSqlSessionFactory")
    @Primary//如果没有使用@Qualify注解指定使用哪一个，优先使用被@Primary注解的bean
    public SqlSessionFactory getOracleSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"oracle",properties);
        return sqlSessionFactory;
    }
}
