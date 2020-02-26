package com.lynx.ssm.service.Impl;

import com.lynx.ssm.dao.ISysLogDao;
import com.lynx.ssm.domain.SysLog;
import com.lynx.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ISysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);

    }


    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}
