package me.caiyuan.spring.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

/**
 * YUAN
 * 7/7/16.
 */
@Configuration
public class BeanScope {

    @Bean(name = "singletonBean")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BeanObject singleton() {
        return new BeanObject();
    }

    @Bean(name = "prototypeBean")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public BeanObject prototype() {
        return new BeanObject();
    }

    // == 使用会话和请求作用域 ======

    @Bean(name = "sessionBean")
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public BeanObject session() {
        return new BeanObject();
    }

    @Bean(name = "requestBean")
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    public BeanObject request() {
        return new BeanObject();
    }

    @Bean(name = "applicationBean")
    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.INTERFACES)
    public BeanObject application() {
        return new BeanObject();
    }

    @Bean(name = "globalSessionBean")
    @Scope(value = WebApplicationContext.SCOPE_GLOBAL_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
    public BeanObject globalSession() {
        return new BeanObject();
    }

}
