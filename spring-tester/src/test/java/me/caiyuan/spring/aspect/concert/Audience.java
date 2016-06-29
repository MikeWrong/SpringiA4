package me.caiyuan.spring.aspect.concert;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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
    @Pointcut("execution(* me.caiyuan.spring.aspect.concert.Performance.perform(..))")
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

}
