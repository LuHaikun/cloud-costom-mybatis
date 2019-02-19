package com.cloud;

import com.cloud.bean.UserInfo;
import com.cloud.mapper.UserMapper;
import com.cloud.sqlSession.MySqlSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        MySqlSession mySqlSession = new MySqlSession();
        UserMapper mapper = mySqlSession.getMapper(UserMapper.class);
        UserInfo userInfo = mapper.getUserInfoById(1);
        System.out.println(userInfo);
    }
}
