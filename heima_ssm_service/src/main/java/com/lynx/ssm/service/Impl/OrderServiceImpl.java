package com.lynx.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lynx.ssm.dao.IOrderDao;
import com.lynx.ssm.domain.Order;
import com.lynx.ssm.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    //分页查询全部订单
    @Override
    public List<Order> findAll(int page,int size) throws Exception {
        //参数pageNum是页码值，参数pageSize是代表每页显示条数
        PageHelper.startPage(page,size);
        return orderDao.findAll();
    }



    //根据id查询订单详情
    @Override
    public Order findById(String orderid)throws  Exception {
        return orderDao.findById(orderid);
    }
}
