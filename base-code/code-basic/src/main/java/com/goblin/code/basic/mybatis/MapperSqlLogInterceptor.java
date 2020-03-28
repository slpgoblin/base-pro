package com.goblin.code.basic.mybatis;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * @program: base-code
 * @description: mapper自定义sql日志拦截器
 * @author: Guojs
 * @create: 2019-10-06 13:01
 **/
@Slf4j
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }),
        @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }) })
public class MapperSqlLogInterceptor implements Interceptor {


    private static final List<Long> SLOW_QUERY_TIME_THREHOLD_LIST = Lists.newArrayList(200L, 100L, 50L, 30L, 20L);

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        long start = System.currentTimeMillis();

        Object returnValue = invocation.proceed();

        log.debug("returnValue:{}", JSONUtil.toJsonStr(returnValue));

        int updateCnt = 0;
        if (returnValue instanceof List) {
            List resultSetList = (List) returnValue;
            updateCnt = resultSetList.size();
        } else if (returnValue instanceof Integer) {
            updateCnt = (Integer) returnValue;
        } else {
            log.error("获取updateCnt失败，returnValue:{}", returnValue);
        }

        long end = System.currentTimeMillis();
        long time = (end - start);

        String sql = showSql(configuration, boundSql);

        log.info("[SQLID]:{}, [TIME]:{}ms, [COUNT]:{}", sqlId, time, updateCnt);
        log.info("[SQL]:{}", sql);

        for (Long threhold : SLOW_QUERY_TIME_THREHOLD_LIST) {
            if (time >= threhold) {
                log.warn("慢查询耗时(>{}ms)：{}ms, sql:{}", threhold, time, sql);
                break;
            }
        }
        return returnValue;
    }

    private static String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return Matcher.quoteReplacement(value);
    }

    private static String showSql(Configuration configuration, BoundSql boundSql) {
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
        if (parameterMappings.size() > 0 && parameterObject != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

            } else {
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }
        }
        return sql;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
