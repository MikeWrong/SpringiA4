package me.caiyuan.spring;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * YUAN
 * 6/28/16.
 */
@Configuration
public class BeanProfile {

    @Bean(destroyMethod = "shutdown")
    @Profile("dev")
    public DataSource dataSource1() {
        return new EmbeddedDatabaseBuilder()
                .addScript("classpath:schema.sql")
                .addScript("classpath:test-data.sql")
                .build();
    }

    @Bean(destroyMethod = "close")
    @Profile("prod")
    public DataSource dataSource2() {
        JndiObjectFactoryBean jndiObjectFactoryBean =
                new JndiObjectFactoryBean();
        jndiObjectFactoryBean.setJndiName("jdbc/myDS");
        jndiObjectFactoryBean.setResourceRef(true);
        jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
        return (DataSource) jndiObjectFactoryBean.getObject();
    }

    @Bean(destroyMethod = "close")
    @Profile("qa")
    public DataSource dataSource3() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:h2:tcp://dbserver/~/test");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("password");
        dataSource.setInitialSize(20);
        dataSource.setMaxActive(30);
        return dataSource;
    }

    @Bean
    @Profile("test1")
    public DataSource dataSource4() {
        return null;
    }

    @Bean
    @Profile("test2")
    public Connection dataSource5() {
        return null;
    }

}
