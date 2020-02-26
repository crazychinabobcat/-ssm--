package com.lynx.ssm.controller;

import com.lynx.ssm.domain.SysLog;
import com.lynx.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    ISysLogService sysLogService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
       List<SysLog> list =  sysLogService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sysLogs",list);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }
}
