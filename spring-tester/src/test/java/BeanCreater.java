import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

// @Configuration 注解该类,等价与XML中配置beans;
// @Bean 标注方法等价于XML中配置bean

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

class D {

    public static String sf = "Static field";
    public String pf = "Public field";

    public D(String info) {
        assertNotNull(info);
        System.out.println("create object D->String");
    }

    public D(Set<String> set, String string, C c) {
        assertNotNull(set);
        assertNotNull(string);
        assertNotNull(c);
        System.out.println("create object D->Set|String|C");
    }

    public D(List<Properties> ps) {
        for (Properties p : ps) {
            assertNotNull(p);
            System.out.println(p);
        }
        System.out.println("call method setProperties");
    }

    public void setA(A a) {
        assertNotNull(a);
        System.out.println("call method setA");
    }

    public void setB(B b) {
        assertNotNull(b);
        System.out.println("call method setB");
    }

    public void setList(List<String> stringList) {
        assertNotNull(stringList);
        System.out.println("call method setList");
    }

}
