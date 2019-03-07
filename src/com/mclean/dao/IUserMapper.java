package com.mclean.dao;

import com.mclean.bean.UserBean;

import java.util.Map;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/4 9:49
 */
public interface IUserMapper {

    // 多参数查询,可以最终封装成一个map
    UserBean selectUserBy(Map<String,Object> map);

    UserBean selectUser(Integer id);

    Boolean insertUser(UserBean user); // 返回值代表的是sql执行影响的行数(L > 0 ? true:false)

    Boolean updateUser(UserBean user);

    Boolean deleteUser(Integer id);

    UserBean selectUserAndDept(Integer id);

    UserBean selectUserStep(Integer id);

    UserBean selectUserFullInfoBySqlStep(Integer id);
}
