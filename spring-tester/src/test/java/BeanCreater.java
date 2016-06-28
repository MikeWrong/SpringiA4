import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.assertNotNull;

// 运行过程中 Spring 容器为 A,B,C 类仅执行一次构建

/**
 * YUAN
 * 6/28/16.
 */
@Configuration
public class BeanCreater {

    // Spring 检测到方法 new B(a()) 和 方法 c(B b) 使用了 @Bean 注解, 并将对象创建的任务交给了 Spring 容器完成.

    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        return new B(a());
    }

    @Bean
    public C c(B b) {
        return new C(b);
    }

}

class A {
    public A() {
        System.out.println("create object A");
    }
}

class B {
    public B(A a) {
        assertNotNull(a);
        System.out.println("create object B");
    }
}

class C {
    public C(B b) {
        assertNotNull(b);
        System.out.println("create object C");
    }
}
