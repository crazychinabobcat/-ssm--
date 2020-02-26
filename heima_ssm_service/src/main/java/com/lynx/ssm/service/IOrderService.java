package com.lynx.ssm.service;

import com.lynx.ssm.domain.Order;

import java.util.List;

public interface IOrderService {

    //分页查询全部订单
    List<Order> findAll(int page,int size) throws  Exception;

    //根据id查询订单详情
    Order findById(String orderid) throws Exception;
}
