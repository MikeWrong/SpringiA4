package me.caiyuan.spring.externals.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * YUAN
 * 7/8/16.
 */
@Configuration
@PropertySource("classpath:SpEl.properties")
public class SpELJavaConfig {

    @Bean
    public BlankDisc blankDisc(@Value("#{'disc.title'}") String title,
                               @Value("#{'disc.artist'}") String artist) {
        return new BlankDisc(title, artist);
    }

}
