package me.caiyuan.spring.aspect.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

// @Aspect 声明切面

/**
 * YUAN
 * 6/29/16.
 */
@Aspect
@Component
public class Audience {

    /**
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(* me.caiyuan.spring.aspect.concert.Performance.perform*(..))")
    public void performance() {
    }

    // 表演之前
    @Before("performance()")
    public void silenceCallPhones() {
        System.out.println("Silencing cell phones");
    }

    // 表演之前
    @Before("performance()")
    public void taskSeats() {
        System.out.println("Taking seats");
    }

    // 表演之后
    @AfterReturning("performance()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP !!!");
    }

    // 表演失败之后
    @AfterThrowing("performance()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }


    // ProceedingJoinPoint 这个对象是必须要有的，因为你要在通知中通过它来调用被通知的方法.
    // 通知方法中可以做任何的事情，当要将控制权交给被通知的方法时，它需要调用 ProceedingJoinPoint 的 proceed() 方法.

    // 环绕通知: 前置通知 + 后置通知 & 失败处理
    @Around("performance()")
    public Object watchPerformance(ProceedingJoinPoint jp) {
        Object result = null;
        try {
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            result = jp.proceed();
            System.out.println("CLAP CLAP CLAP !!!");
        } catch (Throwable t) {
            System.out.println("Demanding a refund");
            t.printStackTrace();
        }
        System.out.println("result >>> " + result);
        return result;
    }

}
