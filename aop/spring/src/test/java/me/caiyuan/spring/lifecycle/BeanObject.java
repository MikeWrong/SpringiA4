package me.caiyuan.spring.lifecycle;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * YUAN
 * 7/7/16.
 */
@Component
public class BeanObject {

    public BeanObject() {
        System.out.println("constructor");
    }

    public void process() {
        System.out.println("process");
    }

    @PostConstruct
    public void init() {
        System.out.println("init");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("destroy");
    }

}
