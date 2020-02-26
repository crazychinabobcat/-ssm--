package com.lynx.ssm.utils;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;


/*
/密码加密工具类
 */
public class BCryptPasswordEncoderUtils {
    private  static BCryptPasswordEncoder  bCryptPasswordEncoder = new  BCryptPasswordEncoder();


    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }



    //密码加密每次加密结果不一样
    public static void main(String[] args) {
//        String password="admin";
//        String newPwd = encodePassword(password);
//        System.out.println(newPwd);


    }


}
