package com.lynx.ssm.service;

import com.lynx.ssm.domain.Role;
import com.lynx.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {

    //查询用户信息
    List<UserInfo> findAll()throws  Exception;

    //保存用户信息
    void save(UserInfo userInfo)throws Exception;

    //根据id查询用户
    UserInfo findById(String id)throws Exception;

    //根据用户id查询可以添加的角色
    List<Role> findOtherRoles(String userid)throws Exception;


    void addRoleToUser(String userId, String[] roleIds);
}
