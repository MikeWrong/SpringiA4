package me.caiyuan.spring.aspect.concert;

import org.springframework.stereotype.Component;

/**
 * YUAN
 * 6/29/16.
 */
@Component
public class Performance {

    public void perform1() {
        System.out.println("Performance.perform1()");
    }

    public String perform2(int i) {
        System.out.println("Performance.perform2(" + i + ")");
        return "String(Performance.perform2()|result)";
    }

}
