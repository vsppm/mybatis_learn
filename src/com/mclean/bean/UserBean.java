package com.mclean.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/1 9:33
 */
public class UserBean implements Serializable {
    public static final long serialVersionUID = 1L;

    Integer id;
    String name;
    String email;
    String nickName;
    // 部门信息
    DeptBean dept;
    // 地址信息
    List<AddressBean> addresses;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public DeptBean getDept() {
        return dept;
    }

    public void setDept(DeptBean dept) {
        this.dept = dept;
    }

    public List<AddressBean> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressBean> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", dept=" + dept +
                ", addresses=" + addresses +
                '}';
    }
}
