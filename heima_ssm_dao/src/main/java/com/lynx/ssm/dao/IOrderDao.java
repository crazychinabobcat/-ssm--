package com.lynx.ssm.dao;

import com.lynx.ssm.domain.Member;
import com.lynx.ssm.domain.Order;
import com.lynx.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {



  //查询全部订单信息
    @Select("select * from orders")
    @Results({
            @Result(id =true,property = "id",column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payTypeStr", column = "payTypeStr"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.lynx.ssm.dao.IProductDao.findById")),
    })
    public List<Order> findAll()throws  Exception;


    @Select("select * from orders where id=#{orderid}")
    @Results({
            @Result(id =true,property = "id",column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payTypeStr", column = "payTypeStr"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.lynx.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberID",javaType = Member.class,one = @One(select = "com.lynx.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.lynx.ssm.dao.ITravellerDao.findByOrdersId"))
    })

    public  Order findById(String orderid) throws Exception;

}
