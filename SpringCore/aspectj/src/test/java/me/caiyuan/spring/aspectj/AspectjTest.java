package me.caiyuan.spring.aspectj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

/**
 * YUAN
 * 6/28/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:AspectJ.xml", loader = GenericXmlContextLoader.class)
public class AspectjTest {

    @Test
    public void t() {
        Performance performance = new Performance();
        performance.perform();
    }

}

