import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// @Configuration 注解该类,等价与XML中配置beans;
// @Bean 标注方法等价于XML中配置bean
// @ComponentScan 默认从本类目录扫描使用了Spring注的解类

/**
 * YUAN
 * 6/28/16.
 */
@Configuration
@ComponentScan
public class BeanCreaterConfiguration {
}
