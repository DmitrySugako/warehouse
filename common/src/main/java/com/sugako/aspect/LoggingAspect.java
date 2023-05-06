package com.sugako.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;



@Component
@Aspect
public class LoggingAspect {

    private static final Logger log=Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.sugako.repository.*.*.*(..))")
    public void aroundRepositoryPointcut(){}

    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("Method "+ joinPoint.getSignature().getName()+ " start");
        Object proceed= joinPoint.proceed();
        log.info("Method "+ joinPoint.getSignature().getName()+ " finished");
        return proceed;
    }

}
