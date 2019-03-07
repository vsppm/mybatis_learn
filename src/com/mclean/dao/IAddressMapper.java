package com.mclean.dao;

import com.mclean.bean.AddressBean;

import java.util.List;

/**
 * @Title:
 * @author: IMUKL
 * @Description:
 * @date: 2019/3/6 15:19
 */
public interface IAddressMapper {
    List<AddressBean> getAddressByUserId(Integer id);
}
