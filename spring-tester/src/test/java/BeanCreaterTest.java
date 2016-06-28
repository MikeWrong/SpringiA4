import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * YUAN
 * 6/28/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanCreater.class, loader = AnnotationConfigContextLoader.class)
public class BeanCreaterTest {

    @Autowired
    private A a;

    @Autowired
    private B b;

    @Autowired
    private C c;

    @Test
    public void t() {
    }

}
