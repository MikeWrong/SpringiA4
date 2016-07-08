package me.caiyuan.spring.externals.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * YUAN
 * 7/8/16.
 */
@Configuration
@PropertySource("classpath:SpEl.properties")
public class SpELJavaConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean(name = "blankDisc1")
    public BlankDisc blankDisc1(@Value("${disc.title}") String title,
                                @Value("${disc.artist}") String artist) {
        return new BlankDisc(title, artist);
    }

    @Bean(name = "blankDisc2")
    public BlankDisc blankDisc2(@Value("#{systemProperties['disc.title']}") String title,
                                @Value("#{systemProperties['disc.artist']}") String artist) {
        return new BlankDisc(title, artist);
    }

}
