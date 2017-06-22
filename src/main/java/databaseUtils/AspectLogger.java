package databaseUtils;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class AspectLogger {
    private Logger logger = Logger.getLogger("bla");

    //@Pointcut("execution(public String get*(..))")
    @Pointcut("execution(public * getQuery* (..))")
    public void queryLog(){}

/*
    public void after(JoinPoint joinPoint){
        logger.warning("sf");

    }
    */
    public void logQueryInfo(String returnedString){
        logger.info("Generated query: " + returnedString);
    }



}
