package com.lynx.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lynx.ssm.domain.Product;
import com.lynx.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;



    //产品添加
    @RequestMapping("/save.do")
    public String save(Product product) throws  Exception{
        product.setId(UUID.randomUUID().toString().replace("-", ""));
        productService.save(product);
        return "redirect:findAll.do";

    }


//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws  Exception{
//
//        ModelAndView modelAndView = new ModelAndView();
//        List<Product> list =  productService.findAll();
//        modelAndView.addObject("productList",list);
//        modelAndView.setViewName("product-list");
//        return modelAndView;
//    }


    //产品查询全部分页
    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")Integer page,@RequestParam(name = "size",required = true,defaultValue = "4")Integer size)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Product> list = productService.findAll(page,size);
        PageInfo pageInfo = new PageInfo(list);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-page-list");
        return  modelAndView;

    }
}
