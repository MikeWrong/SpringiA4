package me.caiyuan.spring.web.config;

import me.caiyuan.spring.web.controller.WebPackage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;

/**
 * YUAN
 * 7/11/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebPackage.class)
public class WebConfig extends WebMvcConfigurerAdapter {

    // 配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    // 配置静态资源的处理
    // Mapped URL path [/**] onto handler of type DefaultServletHttpRequestHandler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 根据给定规则配置静态资源的处理
    // Mapped URL path [/resources/**] onto handler of type ResourceHttpRequestHandler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    // 配置 multipart 解析器
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        // Require servlet 3.0 or higher
        return new StandardServletMultipartResolver();

        // CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));
        // return multipartResolver;
    }

}
