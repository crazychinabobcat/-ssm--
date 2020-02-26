package com.lynx.ssm.dao;

import com.lynx.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {

    //根据id查询产品信息
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;

    //查询所有的订单信息
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //保存订单信息
    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
