package me.caiyuan.spring.qualifier.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * YUAN
 * 7/7/16.
 */
@Configuration
public class Zoo {

    // @Primary 标记首选的 bean

    @Bean
    @Primary
    public Animal dog() {
        return new Dog();
    }

    // @Qualifier 为 bean 自定义限定符

    @Bean
    @Qualifier("duck")
    public Animal duck() {
        return new Duck();
    }

    // @Bean(name = "frog") 手动设置 bean 的 id, 可作为 @Qualifier 的默认限定符

    @Bean(name = "frog")
    public Animal frog() {
        return new Frog();
    }

}
