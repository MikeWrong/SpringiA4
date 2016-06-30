package me.caiyuan.spring.aspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * YUAN
 * 6/29/16.
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class ConcertJavaConfig {

    /*
    @Bean
    public Audience audience() {
        return new Audience();
    }
    */

}
