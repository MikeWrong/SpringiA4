package me.caiyuan.spring.externals.spel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

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

    @Bean(name = "blankDisc3")
    public BlankDisc blankDisc3(@Value("#{'Sgt. Peppers Lonely Hearts Club Band'}") String title,
                                @Value("#{'The Beatles'}") String artist) {
        return new BlankDisc(title, artist);
    }

    @Bean
    public Properties properties(@Value("#{'SpEL.properties'}") String location) {
        PropertiesFactoryBean properties = new PropertiesFactoryBean();
        Resource resource = new ClassPathResource(location);
        properties.setLocation(resource);
        properties.setSingleton(false);
        try {
            return properties.getObject();
        } catch (IOException e) {
            e.printStackTrace();
            return new Properties();
        }
    }

}
