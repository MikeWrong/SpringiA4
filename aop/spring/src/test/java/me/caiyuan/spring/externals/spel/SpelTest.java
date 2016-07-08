package me.caiyuan.spring.externals.spel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * YUAN
 * 7/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpELJavaConfig.class)
public class SpELTest {

    @Autowired
    private BlankDisc blankDisc;

    @Test
    public void t() {
        System.out.println(blankDisc);
    }

}
