package com.lynx.ssm.service.Impl;

import com.github.pagehelper.PageHelper;
import com.lynx.ssm.dao.IProductDao;
import com.lynx.ssm.domain.Product;
import com.lynx.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {


    @Autowired
    private IProductDao productDao;

    //查询全部订单信息
    @Override
    public List<Product> findAll(int page,int size) throws Exception {
        //参数pageNum是页码值，参数pageSize是代表每页显示条数
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }


    //保存产品信息
    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);

    }
}
