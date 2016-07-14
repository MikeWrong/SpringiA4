package me.caiyuan.spring.web.config;

import me.caiyuan.spring.web.RootPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.context.annotation.FilterType.ANNOTATION;

/**
 * YUAN
 * 7/11/16.
 */
@Configuration
@ComponentScan(
        basePackageClasses = RootPackage.class,
        excludeFilters = {@Filter(type = ANNOTATION, value = EnableWebMvc.class)})
@ImportResource("classpath:root-context.xml")
public class RootConfig {
}
