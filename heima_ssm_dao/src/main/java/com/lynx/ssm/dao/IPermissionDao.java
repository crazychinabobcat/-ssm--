package com.lynx.ssm.dao;

import com.lynx.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IPermissionDao {


    //查询所有权限
    @Select("select * from permission")
    List<Permission> findAll()throws Exception;


    //添加权限
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;
}
