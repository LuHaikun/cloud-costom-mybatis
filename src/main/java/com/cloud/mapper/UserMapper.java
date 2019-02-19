package com.cloud.mapper;

import com.cloud.bean.UserInfo;

/**
 * @Created with IntelliJ IDEA.
 * @Description: cloud-costom-mybatis
 * @Author: luhk
 * @Date: 2018-12-20
 * @Time: 10:33 AM
 * @Version: 1.0
 */
public interface UserMapper {
    public UserInfo getUserInfoById(int i);
}
