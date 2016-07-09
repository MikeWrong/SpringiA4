package me.caiyuan.spring.profile.animal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * YUAN
 * 7/7/16.
 */
@Configuration
public class Zoo {

    @Bean
    @Profile("duck")
    public Animal duck() {
        return new Duck();
    }

}
