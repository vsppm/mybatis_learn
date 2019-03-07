package com.mclean.bean;

import java.io.Serializable;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/6 15:15
 */
public class AddressBean implements Serializable{
    public static final long serialVersionUID = 1L;

    Integer id;
    String city;
    Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", userId=" + userId +
                '}';
    }
}
