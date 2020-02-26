package com.lynx.ssm.dao;

import com.lynx.ssm.domain.Permission;
import com.lynx.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {


    //操作角色,根据用户ID查询出所有对应的角色findRoleByUserId
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(property ="roleName" ,column = "roleName"),
            @Result(property ="roleDesc" ,column = "roleDesc"),
            @Result(property ="permissions" ,column = "id",javaType = java.util.List.class,many = @Many(select = "com.lynx.ssm.dao.IPermissions.findPermissionByRoleId"))

    })
    public List<Role> findRoleByUserId(String userid)throws Exception;


    //查询所有角色
    @Select("select * from role")
    List<Role> findAll() throws Exception;

    //添加角色
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role)throws Exception;


    //添加权限
    @Select("select * from role where id=#{roleId}")
    Role findById(String roleId);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermissionToRole(@Param("roleId") String roleId ,@Param("permissionId") String permissionId);
}
