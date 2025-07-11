package com.weien.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {
    @Pointcut("execution(* com.weien.service.*Service.*(..))")
    private void pt(){}

    @Around("pt()")
    public Object fun(ProceedingJoinPoint pjp)throws Throwable{
        //获取形参
//        System.out.println("获取形参");
//        Object[] args = pjp.getArgs();
//        System.out.println(Arrays.toString(args));

        Signature signature = pjp.getSignature();
        String name = signature.getName();
        String className = signature.getDeclaringTypeName();
        //System.out.println("before "+name+" in "+className + "66666666");
        Object obj =  pjp.proceed();
        //System.out.println("after "+name+" in "+className);
        return obj;
    }
}
