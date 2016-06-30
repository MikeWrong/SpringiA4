package me.caiyuan.spring.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// @EnableAspectJAutoProxy 启用 AspectJ 自动代理

/**
 * YUAN
 * 6/29/16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConcertJavaConfig {

    // 声明 Audience bean
    /*
    @Bean
    public Audience audience() {
        return new Audience();
    }
    */

}
