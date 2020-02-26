package com.lynx.ssm.service;

import com.lynx.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    //日志保存到数据库
    public void save(SysLog sysLog) throws Exception;

    //查询所有日志
    List<SysLog> findAll()throws Exception;
}
