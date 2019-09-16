package xyz.qinian.esport.domain;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userId;

    private String tel;

    private String password;

    private String userName;

    private String sex;

    private String headPath;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String tel, String password, String userName, String sex, String headPath) {
        this.tel = tel;
        this.password = password;
        this.userName = userName;
        this.sex = sex;
        this.headPath = headPath;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    } // trim() 方法用于删除字符串的头尾空白符

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath == null ? null : headPath.trim();
    }
}