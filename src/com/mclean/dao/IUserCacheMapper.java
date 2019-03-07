package com.mclean.dao;

import com.mclean.bean.UserBean;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/7 15:51
 */
public interface IUserCacheMapper {
    UserBean selectCacheUser(Integer id);
}
