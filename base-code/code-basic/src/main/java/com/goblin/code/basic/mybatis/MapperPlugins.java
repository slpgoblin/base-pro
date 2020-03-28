package com.goblin.code.basic.mybatis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Properties;

/**
 * @program: base-code
 * @description: 自定义插件
 * @author: Guojs
 * @create: 2020-03-28 10:08
 **/
@Configuration
@MapperScan("com.goblin.code.basic.dao")
public class MapperPlugins {

    /**
     * 自定义插件
     */
    @Bean
    public MapperSqlLogInterceptor mapperInterceptor() {
        MapperSqlLogInterceptor sqlLogInterceptor = new MapperSqlLogInterceptor();
        Properties properties = new Properties();
        sqlLogInterceptor.setProperties(properties);
        return sqlLogInterceptor;
    }

}
