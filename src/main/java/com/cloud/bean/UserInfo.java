package com.cloud.bean;

/**
 * @Author: luhk
 * @Email lhk2014@163.com
 * @Date: 2018/12/20 10:30 AM
 * @Description:
 * @Created with cloud-costom-mybatis
 * @Version: 1.0
 */
public class UserInfo {
    private Integer id;
    private String username;
    private String secretcode;
    private String email;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecretcode() {
        return secretcode;
    }

    public void setSecretcode(String secretcode) {
        this.secretcode = secretcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", secretcode='" + secretcode + '\'' +
                ", email='" + email + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
