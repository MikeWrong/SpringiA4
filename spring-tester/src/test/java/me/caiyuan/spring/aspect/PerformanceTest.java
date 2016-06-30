package me.caiyuan.spring.aspect;

import me.caiyuan.spring.aspect.concert.Performance;
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
@ContextConfiguration(locations = {"classpath:AspectConcert.xml"}, loader = DelegatingSmartContextLoader.class)
public class PerformanceTest {

    @Autowired
    private Performance performance;

    @Test
    public void t() {
        performance.perform1();
        System.out.println("==================");
        performance.perform2(123);
    }

}
