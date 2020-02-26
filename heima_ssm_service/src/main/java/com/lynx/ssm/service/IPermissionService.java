package com.lynx.ssm.service;

import com.lynx.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    //查询所有权限
    List<Permission> findAll() throws Exception;

    //添加权限
    void save(Permission permission)throws Exception;
}
