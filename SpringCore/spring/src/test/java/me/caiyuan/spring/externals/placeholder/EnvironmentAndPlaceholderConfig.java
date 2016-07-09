package me.caiyuan.spring.externals.placeholder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Configuration
@PropertySource({"classpath:Externals1.properties", "classpath:Externals2.properties"})
public class EnvironmentAndPlaceholderConfig {

    @Autowired
    Environment env;

    @Value("${app.name}")
    String appName;

    // 声明使用 PropertySourcesPlaceholderConfigurer ${...} 解析占位符
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public BlankDisc blankDisc(@Value("${disc.title}") String title,
                               @Value("${disc.artist}") String artist) {
        return new BlankDisc(title, artist);
    }

    @PostConstruct
    public void init() {
        System.out.println(appName);
        float number = env.getProperty("float", Float.class);
        System.out.println(number);
        Class<BlankDisc> clz = env.getPropertyAsClass("class", BlankDisc.class);
        System.out.println(clz.getPackage());
    }

    @PreDestroy
    public void destroy() {
        System.out.println(env.containsProperty("app.name"));
    }

}
