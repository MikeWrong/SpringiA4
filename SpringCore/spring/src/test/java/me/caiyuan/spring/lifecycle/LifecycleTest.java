package me.caiyuan.spring.lifecycle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * YUAN
 * 7/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LifecycleJavaConfig.class)
public class LifecycleTest {

    @Autowired
    private BeanObject beanObject;

    @Test
    public void t() {
        beanObject.process();
    }

}
