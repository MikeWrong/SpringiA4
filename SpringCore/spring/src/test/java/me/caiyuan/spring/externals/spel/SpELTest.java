package me.caiyuan.spring.externals.spel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Properties;

/**
 * YUAN
 * 7/8/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpELJavaConfig.class)
public class SpELTest {

    @Autowired
    @Qualifier("blankDisc1")
    private BlankDisc blankDisc1;

    @Autowired
    @Qualifier("blankDisc2")
    private BlankDisc blankDisc2;

    @Autowired
    @Qualifier("blankDisc3")
    private BlankDisc blankDisc3;

    @Value("#{systemProperties}")
    private Map<String, String> systemProperties;

    @Value("#{systemEnvironment}")
    private Map<String, String> systemEnvironment;

    @Value("#{environment}")
    private Environment env;

    @Value("#{environment.getProperty('disc.title')}")
    private String discTitle;

    @Autowired
    private Properties properties;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:SpEL.xml");
        BlankDisc blankDisc1 = (BlankDisc) applicationContext.getBean("blankDisc1");
        System.out.println(blankDisc1);
        BlankDisc blankDisc2 = (BlankDisc) applicationContext.getBean("blankDisc2");
        System.out.println(blankDisc2);
        BlankDisc blankDisc3 = (BlankDisc) applicationContext.getBean("blankDisc3");
        System.out.println(blankDisc3);

        Object properties = applicationContext.getBean("properties");
        System.out.println(properties);
    }

    @Test
    public void t() {
        System.out.println(blankDisc1);
        System.out.println(blankDisc2);
        System.out.println(blankDisc3);
        System.out.println("----------------------");
        for (Map.Entry<String, String> entry : systemProperties.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("----------------------");
        for (Map.Entry<String, String> entry : systemEnvironment.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("----------------------");
        System.out.println(env);
        System.out.println(env.getProperty("disc.title"));
        System.out.println(discTitle);
        System.out.println("----------------------");
        System.out.println(properties);
    }

}
