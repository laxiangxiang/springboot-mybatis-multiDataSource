package com.example.demo.config.mybatismultidatasourcefirstway;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by LXX on 2018/11/26.
 * mybatis多数据源配置方式一
 */
//@Configuration
public class MybatisConfig {

    private static final String resource = "mybatis-config.xml";

    private static final String mybatisProperties = "mybatis.properties";

    private static Properties properties = null;

//////////////////把mybatis.properties文件中的属性值传入SQLSessionFactory中的两种方法///////////////////////////////////////////////////////////////////////
//     * 方法一：使用java配置。在config.java中获取mybatis.properties文件的Properties对象，在创建SQLSessionFactory时把他作为参数传入。                         //
//     * 方法二：在mybatis-config.xml配置文件中使用<properties><properties/>标签中的resource或者url传入。            //
//     * 方法三：在mybatis-config.xml配置文件中使用<properties><properties/>标签中的子标签<properties name= value= />设置属性值                          //
//     * 优先级：                                                                                                                                //
//          在 properties 元素体内指定的属性首先被读取。(最低)                                                                                       //
//          然后根据 properties 元素中的 resource 属性读取类路径下属性文件或根据 url 属性指定的路径读取属性文件，并覆盖已读取的同名属性。 （中等）              //
//          最后读取作为方法参数传递的属性，并覆盖已读取的同名属性。（最高）                                                                            //
//          因此，通过方法参数传递的属性具有最高优先级，resource/url 属性中指定的配置文件次之，最低优先级的是 properties 属性中指定的属性                    //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
        //方法一
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql",properties);
        //方法二
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"mysql");
        return sqlSessionFactory;
    }

    @Bean(name = "oracleSqlSessionFactory")
    @Primary//如果没有使用@Qualify注解指定使用哪一个，优先使用被@Primary注解的bean
    public SqlSessionFactory getOracleSqlSessionFactory() throws IOException{
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //方法一
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"oracle",properties);
        //方法二
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream,"oracle");
        return sqlSessionFactory;
    }
}
