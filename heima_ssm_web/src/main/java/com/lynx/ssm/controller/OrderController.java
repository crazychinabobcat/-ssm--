package com.lynx.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.lynx.ssm.domain.Order;
import com.lynx.ssm.service.IOrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;


//    //查询全部订单数据 --未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll()throws  Exception{
//        ModelAndView modelAndView = new ModelAndView();
//        List<Order> orderList = orderService.findAll();
//        modelAndView.addObject("ordersList",orderList);
//        modelAndView.setViewName("orders-list");
//        return modelAndView;
//    }

        //查询全部订单数据
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "4") Integer size)throws  Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orderList = orderService.findAll(page,size);
        //PageInfo分页bean
        PageInfo pageInfo = new PageInfo(orderList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("orders-page-list");
        return  modelAndView;

    }

    //根据id查询订单详情
    @RequestMapping("/findById.do")
    public  ModelAndView findById(@RequestParam(name = "id",required = true)String  orderid) throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderService.findById(orderid);
        modelAndView.addObject("orders",order);
        modelAndView.setViewName("orders-show");
        return modelAndView;

    }

}
