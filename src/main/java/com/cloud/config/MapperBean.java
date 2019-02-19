package com.cloud.config;

import java.util.List;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 11:06 AM
 * @Description:
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class MapperBean {
    //接口名称
    private String interfaceName;
    //接口下所有方法
    private List<Function> list;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getList() {
        return list;
    }

    public void setList(List<Function> list) {
        this.list = list;
    }
}
