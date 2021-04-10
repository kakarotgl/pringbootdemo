package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 2、声明一个Aspect
 * Aspect注解需要导入aspectj包
 */
@Component
@Aspect
public class UserAspect {
    /**
     *3、申明一个pointCut
     */
    @Pointcut("execution(* com.example.demo.service..*.*(..))")
    public void pintCut(){

    }
    /**4、申明一个Advice通知
     * * 申明before通知,在pintCut切入点前执行
     * * 通知与切入点表达式相关联，
     * * 并在切入点匹配的方法执行之前、之后或前后运行。
     * * 切入点表达式可以是对指定切入点的简单引用，也可以是在适当位置声明的切入点表达式。
     * */
    @Before("pintCut()")
    public void beforeAdvice(){
        System.out.println("before--query");
    }

    @After("pintCut()")
    public void afterAdvice(){
        System.out.println("after--query");
    }
    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("pintCut()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知的方法名："+proceedingJoinPoint.getSignature().getName());
        try {
            //调用方法
            Object obj = proceedingJoinPoint.proceed();
            System.out.println("方法是否有返回值："+obj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
