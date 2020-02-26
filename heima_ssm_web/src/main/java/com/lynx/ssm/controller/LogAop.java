package com.lynx.ssm.controller;

import com.lynx.ssm.domain.SysLog;
import com.lynx.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    //web.xml配置RequestContextListener监听器
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz; //访问的类
    private Method method; //访问的方法

    //前置通知 获取开始时间，执行的；类是哪个，执行的是哪个方法
    @Before("execution(* com.lynx.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws Exception{

        visitTime = new Date(); //当前时间就是访问时间
        clazz = joinPoint.getTarget().getClass(); //具体要访问的类
        String methodName = joinPoint.getSignature().getName();//获取这个方法的名字
        Object[] args = joinPoint.getArgs(); //获取方法的参数

        //获取具体执行方法的method对象
        if (args == null || args.length ==0) {
            method = clazz.getMethod(methodName); //根据当前的方法名字获取方法
        }else {
            Class[] classArgs = new  Class[args.length];
            for ( int i = 0; i<args.length ;i++){
                classArgs[i] = args[i].getClass();
            }
                clazz.getMethod(methodName,classArgs);
        }
        }

     //后置通知
    @After("execution(* com.lynx.ssm.controller.*.*(..))")
    public  void doAfter(JoinPoint joinPoint)throws Exception{
        Long time = new Date().getTime() - visitTime.getTime(); //获取访问的时长
        //获取url
        String url ="";
        if (clazz!=null && method!=null &&clazz!= LogAop.class){

            //获取类上的@RequestMapping("/xxxxx")
            RequestMapping classAnnotation =(RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation !=null){
               String[] classValue =  classAnnotation.value();

               //获取方法上的@RequestMapping("/xxxx")
                RequestMapping methodAnnotation =  method.getAnnotation(RequestMapping.class);
                if (methodAnnotation!=null){
                    String[] methodValue = methodAnnotation.value();

                    url = classValue[0]+methodValue[0];

                    //获取访问的ip地址
                    String ip = request.getRemoteUser();
                    //获取当前操作的用户
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将对象封装到sysLog里面
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    //调用service层,保存到数据库
                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
