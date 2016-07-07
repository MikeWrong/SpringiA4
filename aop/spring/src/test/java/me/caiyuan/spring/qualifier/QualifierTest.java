package me.caiyuan.spring.qualifier;

import me.caiyuan.spring.qualifier.animal.Animal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * YUAN
 * 7/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = QualifierJavaConfig.class)
public class QualifierTest {

    @Autowired
    private Animal animalPrimary;

    // @Qualifier 选择想要注入的 bean 的 id 或 使用 @Qualifier 自定义的限定符

    @Autowired
    @Qualifier("frog")
    private Animal animal_1;

    @Autowired
    @Qualifier("duck")
    private Animal animal_2;

    // @Aquatic 使用自定义注解 限定符

    @Autowired
    @Aquatic
    private Animal animal_3;

    @Test
    public void t() {
        animalPrimary.speak();
        animal_1.speak();
        animal_2.speak();
        animal_3.speak();
    }

}
