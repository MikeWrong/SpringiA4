
###通过注解引入新功能

通过 @DeclareParents 注解，将 Encoreable 接口引入到 Performance bean 中。

```java
    @DeclareParents(value = "me.caiyuan.spring.aspect.concert.Performance+", defaultImpl = DefaultEncoreable.class)
```

- value 属性指定了哪种类型的 bean 要引入该接口。
  在本例中，也就是所有实现 Performance 的类型。（标记符后面的加号表示是 Performance 的所有子类型，而不是 Performance 本身。）

- defaultImpl 属性指定了为引入功能提供实现的类。在这里，我们指定的是 DefaultEncoreable 提供实现。

- @DeclareParents 注解所标注的静态属性指明了要引入了接口。在这里，我们所引入的是 Encoreable 接口。

###激活 profile

- 作为JVM的系统属性

```java
    System.setProperty("spring.profiles.activate", "duck");
    System.setProperty("spring.profiles.default", "dog");
    // 激活 profile
    // 如果 spring.profiles.activate 没有设置,则使用默认设置 spring.profiles.default; 如果这两个都没有设置, 那么就没有激活 profile
```

- 在集成测试类上,使用 @ActiveProfiles

```java
    @RunWith(SpringJUnit4ClassRunner.class)
    @ActiveProfiles("duck")
```

- 作为环境变量 Environment

```shell
    spring.profile.default=dog
```

- 作为 DispatcherServlet 的初始化参数

```xml
    <servlet>
        <servlet-name>dispatcherServlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>spring.profile.default</param-name>
            <param-value>dog</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
```

- 作为 Web 应用的上下文参数

```xml
    <context-param>
       <param-name>spring.profiles.default</param-name>
       <param-value>dog</param-value>
    </context-param>
```
