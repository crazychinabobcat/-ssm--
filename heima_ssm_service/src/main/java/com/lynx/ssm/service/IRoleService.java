package com.lynx.ssm.service;

import com.lynx.ssm.domain.Permission;
import com.lynx.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    //查询所有角色
    public List<Role> findAll()throws Exception;

     //添加角色
    void save(Role role) throws Exception;


    //添加权限
    Role findById(String roleId)throws  Exception;

    List<Permission> findOtherPermission(String roleId)throws  Exception;

    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}
