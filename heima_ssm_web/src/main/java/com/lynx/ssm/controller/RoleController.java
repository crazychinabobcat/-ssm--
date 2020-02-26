package com.lynx.ssm.controller;


import com.lynx.ssm.domain.Permission;
import com.lynx.ssm.domain.Role;
import com.lynx.ssm.domain.UserInfo;
import com.lynx.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;



    //查询所有角色
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {

        ModelAndView modelAndView =new ModelAndView();
        List<Role> list =  roleService.findAll();
        modelAndView.addObject("roleList",list);
        modelAndView.setViewName("role-list");
        return  modelAndView;

    }


    //保存角色
    @RequestMapping("/save.do")
    public String save(Role role) throws Exception{
        roleService.save(role);
        return "redirect:findAll.do";
    }


 //添加权限
    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public  ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId)throws Exception{
        ModelAndView modelAndView  = new ModelAndView();
        //根据roleid查询role
        Role role =  roleService.findById(roleId);
        //根据roleId查询添加的权限
        List<Permission> list = roleService.findOtherPermission(roleId);
        modelAndView.addObject("role",role);
        modelAndView.addObject("permissionList",list);
        modelAndView.setViewName("role-permission-add");
        return modelAndView;
    }


    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "ids",required = true) String[]permissionIds )throws Exception{
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:findAll.do";
    }


}
