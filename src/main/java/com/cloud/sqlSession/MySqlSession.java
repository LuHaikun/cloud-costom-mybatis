package com.cloud.sqlSession;

import java.lang.reflect.Proxy;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 11:26 AM
 * @Description:
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class MySqlSession {
    private Executor executor = new MyExecutor();
    private MyConfiguration configuration = new MyConfiguration();
    public <T> T selectOne (String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    public <T> T getMapper (Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[]{clazz},
                new MyMapperProxy(configuration,this));
    }
}
