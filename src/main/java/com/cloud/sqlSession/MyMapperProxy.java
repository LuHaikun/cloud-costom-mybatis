package com.cloud.sqlSession;

import com.cloud.config.Function;
import com.cloud.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 11:32 AM
 * @Description:
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class MyMapperProxy implements InvocationHandler{
    private MyConfiguration configuration;
    private MySqlSession mySqlSession;
    public MyMapperProxy(MyConfiguration configuration, MySqlSession mySqlSession) {
        this.configuration = configuration;
        this.mySqlSession = mySqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean readMapper = configuration.readMapper("UserMapper.xml");
        //是否xml文件对应的接口
        if(!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())){
            throw new RuntimeException("UserMapper对应方法名错误");
        }
        List<Function> list = readMapper.getList();
        if(list!=null && list.size()>0){
            for (Function function : list){
                if(method.getName().equals(function.getFuncName())){
                    return mySqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                }
            }
        }
        return null;
    }
}
