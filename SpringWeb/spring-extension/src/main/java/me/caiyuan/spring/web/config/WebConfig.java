package me.caiyuan.spring.web.config;

import me.caiyuan.spring.web.controller.WebPackage;
import me.caiyuan.spring.web.filter.CustomFilter;
import me.caiyuan.spring.web.servlet.CustomServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * YUAN
 * 7/11/16.
 */
@Configuration
// 启用 SpringMVC
@EnableWebMvc
// 启用组件扫描
@ComponentScan(basePackageClasses = WebPackage.class)
public class WebConfig extends WebMvcConfigurerAdapter implements WebApplicationInitializer {

    // 配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    @Bean
    public MappingJackson2JsonView mappingJackson2JsonView() {
        return new MappingJackson2JsonView();
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

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic customServlet = servletContext.addServlet("customServlet", CustomServlet.class);
        customServlet.addMapping("/customServlet");

        FilterRegistration.Dynamic customFilter = servletContext.addFilter("customFilter", CustomFilter.class);
        customFilter.addMappingForServletNames(null, false, "customServlet");
    }

}

