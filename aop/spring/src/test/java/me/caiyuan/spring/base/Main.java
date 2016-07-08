package me.caiyuan.spring.base;

import me.caiyuan.spring.base.pkg2.Obj;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * YUAN
 * 7/8/16.
 */
public class Main {

    static class t1 {
        public static void main(String[] args) {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:Base1.xml");
            Obj obj = (Obj) ctx.getBean("obj");
            System.out.println(obj.a);
        }
    }

    static class t2 {
        public static void main(String[] args) {
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:Base2.xml");
            Obj obj = (Obj) ctx.getBean("obj");
            System.out.println(obj.a);
        }
    }

}
