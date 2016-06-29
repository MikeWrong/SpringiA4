package me.caiyuan.spring.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * YUAN
 * 6/29/16.
 */
@Configuration
@ComponentScan

@Aspect
public class Audience {

    // 表演之前
    @Before("execution(** concert.Performance.perform(..))")
    public void silenceCallPhones() {
        System.out.println("Silencing cell phones");
    }

    // 表演之前
    @Before("execution(** concert.Performance.perform(..))")
    public void taskSeats() {
        System.out.println("Taking seats");
    }

    // 表演之后
    @AfterReturning("execution(** concert.Performance.perform(..))")
    public void applause() {
        System.out.println("CLAP CLAP CLAP !!!");
    }

    // 表演失败之后
    @AfterThrowing("execution(** concert.Performance.perform(..))")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

}
