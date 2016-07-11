package me.caiyuan.spring.web.config;

import me.caiyuan.spring.web.controller.HomeController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

/**
 * YUAN
 * 7/11/16.
 */
@Configuration
@ComponentScan(
        basePackageClasses = HomeController.class,
        excludeFilters = {@Filter(type = ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}
