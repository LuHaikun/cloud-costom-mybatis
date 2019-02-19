package com.cloud.sqlSession;

import com.cloud.bean.UserInfo;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 11:27 AM
 * @Description: 自定义执行类
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class MyExecutor implements Executor {
    private MyConfiguration configuration = new MyConfiguration();
    @Override
    public <T> T query(String statement, Object parameter) {
        Connection connection = getConnection();
        ResultSet resultset = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(statement);
            preparedStatement.setString(1,parameter.toString());
            resultset = preparedStatement.executeQuery();
            UserInfo userInfo = new UserInfo();
            while (resultset.next()) {
                Field[] fields = userInfo.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    field.set(userInfo, resultset.getObject(field.getName()));
                }
            }
            return (T) userInfo;
        } catch (SQLException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            try {
                if(resultset != null){
                    resultset.close();
                }
                if(preparedStatement !=null){
                    preparedStatement.close();
                }
                if(connection !=null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public Connection getConnection() {
        return configuration.build("mybatis-config.xml");
    }
}
