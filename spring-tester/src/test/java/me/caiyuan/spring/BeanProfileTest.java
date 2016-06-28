package me.caiyuan.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.sql.DataSource;
import java.sql.Connection;

// @ActiveProfiles({"test1", "test2"}) 启用 Profile 配置 "test1", "test2"

/**
 * YUAN
 * 6/28/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanProfile.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles({"test1", "test2"})
public class BeanProfileTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private Connection connection;

    @Test
    public void t() {
        System.out.println(dataSource);
        System.out.println(connection);
    }

}
