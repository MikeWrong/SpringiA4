package me.caiyuan.spring.autowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertNotNull;

/**
 * YUAN
 * 6/28/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = BeanCreaterXmlConfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(classes = BeanCreaterJavaConfig.class, loader = AnnotationConfigContextLoader.class)
public class BeanCreaterTest {

    @Autowired
    private A a;

    @Autowired
    private B b;

    @Autowired
    private C c;

    @Test
    public void t() {
        assertNotNull(a);
        assertNotNull(b);
        assertNotNull(c);
    }

}

class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("Autowired.xml");
        applicationContext.start();
    }

}