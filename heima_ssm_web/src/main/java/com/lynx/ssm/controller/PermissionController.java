package com.lynx.ssm.controller;

import com.lynx.ssm.domain.Permission;
import com.lynx.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //查找所有权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> list = permissionService.findAll();
        modelAndView.addObject("permissionList",list);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    //添加权限
    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception{
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
}
