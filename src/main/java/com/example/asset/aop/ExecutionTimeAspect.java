package com.example.asset.aop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeAspect {
    Logger myLogger = LogManager.getLogger(ExecutionTimeAspect.class);

    @Around("@annotation(com.example.asset.aop.LogExecutionTime)")
    public Object calculateExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj  = null ;
     try {
          obj = proceedingJoinPoint.proceed();
     }finally {
         long duration = System.currentTimeMillis() - start;
         String methodName = proceedingJoinPoint.getSignature().getName();

         myLogger.info("method [" + methodName + "] executed in [ " + duration + " ] ms ");

     }
        return obj;

    }
}
