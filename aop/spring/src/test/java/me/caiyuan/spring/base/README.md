
JavaConfig , XmlConfig 混合使用
--

### 方案一
```java
    public class A {
    }

    public class Obj {
        @Autowired
        public A a;
    }
```
```xml
    <!--
        <context:annotation-config/> 用于激活那些已经在 spring 容器里注册过的 bean（无论是通过 xml 的方式还是通过 package scan 的方式）上面的注解(如: @Autowired 等等).
    -->
    <context:annotation-config/>

    <bean class="me.caiyuan.spring.base.pkg1.A"/>
    <bean id="obj" class="me.caiyuan.spring.base.pkg2.Obj"/>
```

### 方案二
```java
    @Component
    public class A {
    }

    @Component
    public class Obj {
        @Autowired
        public A a;
    }
```
```xml
    <!--
        <context:component-scan> 除了具有 <context:annotation-config> 的功能之外, 还可以在指定的 package 下扫描以及注册 JavaBean 。
    -->
    <context:component-scan base-package="me.caiyuan.spring.base.pkg1, me.caiyuan.spring.base.pkg2"/>
```