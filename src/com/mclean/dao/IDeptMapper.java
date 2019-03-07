package com.mclean.dao;

import com.mclean.bean.DeptBean;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/6 12:14
 */
public interface IDeptMapper {
    DeptBean selectDept(Integer id);
}
