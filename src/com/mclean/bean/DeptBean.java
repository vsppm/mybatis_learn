package com.mclean.bean;

import java.io.Serializable;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/6 9:28
 */
public class DeptBean implements Serializable {
    public static final long serialVersionUID = 1L;

    Integer id;
    String deptName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "DeptBean{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
