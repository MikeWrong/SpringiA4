package me.caiyuan.spring.profile;

import me.caiyuan.spring.profile.animal.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DelegatingSmartContextLoader;

/**
 * YUAN
 * 7/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:Profile.xml", loader = DelegatingSmartContextLoader.class)
@ContextConfiguration(classes = ProfileJavaConfig.class, loader = DelegatingSmartContextLoader.class)
@ActiveProfiles("duck")
public class ProfileTest {

    @Autowired
    private Animal animal;

    public static void main(String[] args) {
        // 激活 profile
        // 如果 spring.profiles.activate 没有设置,则使用默认设置 spring.profiles.default; 如果这两个都没有设置, 那么就没有激活 profile
        // System.setProperty("spring.profiles.activate", "duck");
        // System.setProperty("spring.profiles.default", "dog");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:Profile.xml");
        Animal animal = (Animal) context.getBean("animal");
        animal.speak();
    }

    @Test
    public void t() {
        animal.speak();
    }

}
