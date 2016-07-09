package me.caiyuan.spring.aspect.concert;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// @Aspect 声明切面

/**
 * YUAN
 * 6/29/16.
 */
@Aspect
@Component
public class Audience {

    private Object arg;

    /**
     * 通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(* me.caiyuan.spring.aspect.concert.Performance.perform*())")
    public void performance1() {
    }

    /**
     * 切点表达式中的args(i)限定符。它表明传递给perform()方法的int类型参数也会传递到通知中去。参数的名称 i 也与切点方法签名中的参数相匹配。
     */
    @Pointcut("execution(* me.caiyuan.spring.aspect.concert.Performance.perform*(int)) && args(i)")
    public void performance2(int i) {
    }

    // 表演之前
//    @Before("performance1()")
    public void silenceCallPhones() {
        System.out.println("Silencing cell phones");
    }

    // 表演之前
//    @Before("performance1()")
    public void taskSeats() {
        System.out.println("Taking seats");
    }

    // 表演之后
//    @AfterReturning("performance1()")
    public void applause() {
        System.out.println("CLAP CLAP CLAP !!!");
    }


    // ProceedingJoinPoint 这个对象是必须要有的，因为你要在通知中通过它来调用被通知的方法.
    // 通知方法中可以做任何的事情，当要将控制权交给被通知的方法时，它需要调用 ProceedingJoinPoint 的 proceed() 方法.

    // 表演失败之后
//    @AfterThrowing("performance1()")
    public void demandRefund() {
        System.out.println("Demanding a refund");
    }

    // 环绕通知: 前置通知 + 后置通知 & 失败处理
    @Around("performance1()")
    public Object watchPerformance1(ProceedingJoinPoint jp) {
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

    public Object getArg() {
        return arg;
    }

    @Around("performance2(int)")
    public Object watchPerformance2(ProceedingJoinPoint jp) {
        Object result = null;
        try {
            System.out.println("Silencing cell phones");
            System.out.println("Taking seats");
            Object[] args = jp.getArgs();
            System.out.println(Arrays.toString(args));
            result = jp.proceed(args);
            arg = args[0];
            System.out.println("CLAP CLAP CLAP !!!");
        } catch (Throwable t) {
            System.out.println("Demanding a refund");
            t.printStackTrace();
        }
        System.out.println("result >>> " + result);
        return result;
    }

}
