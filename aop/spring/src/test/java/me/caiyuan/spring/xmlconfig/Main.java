package me.caiyuan.spring.xmlconfig;

import me.caiyuan.spring.xmlconfig.encoreable.Encoreable;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * YUAN
 * 7/5/16.
 */
public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:Xmlconfig.xml");

        Performance performance = (Performance) applicationContext.getBean("performance");
        performance.perform();
        System.out.println("----------------------");
        performance.perform(123);
        System.out.println("----------------------");
        Class<?>[] clz = performance.getClass().getInterfaces();
        for (Class<?> c : clz) {
            System.out.println(c);
        }
        Encoreable encoreable = (Encoreable) performance;
        encoreable.performEncore();
    }

}
