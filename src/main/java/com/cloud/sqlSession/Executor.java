package com.cloud.sqlSession;

/**
 * @Created with IntelliJ IDEA.
 * @Description: cloud-costom-mybatis
 * @Author: luhk
 * @Date: 2018-12-20
 * @Time: 11:27 AM
 * @Version: 1.0
 */
public interface Executor {
    public <T> T query (String statement, Object parameter);
}
