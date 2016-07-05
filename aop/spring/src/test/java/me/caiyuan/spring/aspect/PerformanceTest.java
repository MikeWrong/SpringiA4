package me.caiyuan.spring.aspect;

import me.caiyuan.spring.aspect.concert.Audience;
import me.caiyuan.spring.aspect.concert.Performance;
import me.caiyuan.spring.aspect.encoreable.Encoreable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DelegatingSmartContextLoader;

/**
 * YUAN
 * 6/29/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = ConcertJavaConfig.class, loader = AnnotationConfigContextLoader.class)
@ContextConfiguration(locations = {"classpath:Aspect.xml"}, loader = DelegatingSmartContextLoader.class)
public class PerformanceTest {

    @Autowired
    private Performance performance;

    @Autowired
    private Audience audience;

    @Autowired
    private Encoreable encoreable;

    @Test
    public void t() {
        System.out.println();
        performance.perform1();
        System.out.println("==================");

        System.out.println();
        performance.perform2(123);
        System.out.println(audience.getArg());
        System.out.println("==================");

        System.out.println();
        Encoreable encoreable = (Encoreable) performance;
        encoreable.performEncore();
        System.out.println("performance.equals(encoreable) = " + performance.equals(encoreable));
        Class<? extends Performance> clz = performance.getClass();
        for (Class<?> c : clz.getInterfaces()) {
            System.out.println(c);
        }
        System.out.println();

    }

}
