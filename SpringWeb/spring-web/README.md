Spring Web
--

#### 跟踪 Spring MVC 的请求
![Dispatcher](dispatcher.png)

1. Request : 带有用户请求的信息, 至少会包含请求的 URL。

2. DispatcherServlet : SpringMVC 与大多数 Java 的 Web 框架一样, 所有的请求都会经过一个前端控制器 Servlet;
前端控制器是常用的 Web 应用程序模式, 在这里单例的 Servlet 将请求委托给应用程序的其他组件来执行实际的处理。
在 SpringMVC 中 DispatcherServlet 就是前端控制器。

3. HandlerMapping : DispatcherServlet 查询一个或多个处理器映射 HandlerMapping 来确定请求的一个站在哪里,
处理器映射会根据请求所携带的URL信息来进行决策。

4. Controller : 一旦选择了合适的 Controller, DispatcherServlet 会将请求发送给选中的控制器, 到了控制器, 请求会卸下其负载(用户提交的信息)
并耐心等待控制器处理这些信息。

5. Model & Logical view name : Controller 完成逻辑处理后, 通常会产生一些信息, 这些信息需要返回给用户并在浏览器上显示。这些信息被称为模型(Model)。
不过仅仅给用户返回原始的信息是不够的--这些信息需要以用户友好的方式进行格式化,一般会是HTML。所以,信息需要发送给一个视图(View),通常会是JSP。

6. Controller : Controller 所做的最后一件事情就是将模型数据打包,并且标示出用于渲染输出的视图名; 它接下来会将请求联通模型和视图名发送回 DispatcherServlet。

7. ViewResolver : DispatcherServlet 将会使用视图解析器(ViewResolver)来将逻辑视图名匹配为一个特定的视图实现, 他可能是JSP 。

8. View : DispatcherServlet 已经知道由哪一个视图渲染结果, 那请求的任务基本上也就完成了; 它的最后一站就是视图的实现, 在这里它交付模型数据, 请求的任务就完成了。

9. Response : 视图将使用模型数据输出, 这个输出会通过响应对象传递给客户端。

### Bootstrap

```java
package me.caiyuan.spring.web.config;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```
```java
package me.caiyuan.spring.web.config;

@Configuration
@ComponentScan(
        basePackageClasses = RootPackage.class,
        excludeFilters = {@Filter(type = ANNOTATION, value = EnableWebMvc.class)})
// 加载 classpath 路径下后缀是 "-context.xml" 的所有 spring 配置文件
@ImportResource("classpath:*-context.xml")
public class RootConfig {
}
```
```java
package me.caiyuan.spring.web.config;

@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebPackage.class)
public class WebConfig extends WebMvcConfigurerAdapter {

    // 配置JSP视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

    // 配置静态资源的处理: Mapped URL path [/**] onto handler of type DefaultServletHttpRequestHandler
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 根据给定规则配置静态资源的处理: Mapped URL path [/resources/**] onto handler of type ResourceHttpRequestHandler
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
```
```java
package me.caiyuan.spring.web.controller;

public interface WebPackage {
}
```
```java
package me.caiyuan.spring.web;

public interface RootPackage {
}
```
```java
package me.caiyuan.spring.web.controller;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = GET)
    public String home() {
        return "home";
    }
}
```
```jsp
<!-- /WEB-INF/views/home.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
spring-web
</body>
</html>
```

- AbstractAnnotationConfigDispatcherServletInitializer 剖析
  在Servlet3.0环境中,容器会在类路径中查找实现javax.servlet.ServletContainerInitializer接口的类,如果发现的话,就会用来配置Servlet容器;
  Spring提供了这个接口的实现 SpringServletContainerInitializer,这个实现类反过来会查找实现WebApplicationInitializer的类并将配置的任务交给它们来完成;
  Spring3.2引入了一个便利的WebApplicationInitializer基础实现AbstractAnnotationConfigDispatcherServletInitializer。

- 在SpringWeb应用中,通常会创建两个应用上下文;
  1). DispatcherServlet 加载包含Web组件的Bean,如控制器、视图解析器以及处理器映射;
  2). ContextLoaderListener 加载应用中的其它Bean,这些Bean通常是驱动应用后端的中间层和数据层组件。
  实际上AbstractAnnotationConfigDispatcherServletInitializer会同时创建DispatcherServlet和ContextLoaderListener;
  1). getRootConfigClasses()方法返回带有@Configuration注解的类将会用来配置ContextLoaderListener创建的应用上下文中的Bean;
  2). getServletConfigClasses()方法返回带有@Configuration注解的类将会用来定义DispatcherServlet创建的应用上下文中的Bean。

- 如果没有配置视图解析器,Spring默认会使用BeanNameViewResolver,这个视图解析器会查找ID与视图名称匹配的Bean,并且查找的Bean要实现View接口,它以这样的方式解析视图。

- WebConfig类扩展了WebMvcConfigraerAdapter并重写了其configureDefaultServletHandling()方法,通过调用DefaultServletHandlerConfigurer的enable()方法,
  我们要求DispatcherServlet将静态资源的请求转发到Servlet容器中默认的Servlet上,而不是使用DispatcherServlet本身来处理此类请求。

### 传递模型数据到视图中

```java
package me.caiyuan.spring.web.controller;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    // 在方法签名中给定了一个Model作为参数,这样就能将Repository中获取的数据填充到模型中;
    // Model 实际上是一个Map,它会传递给视图,这样数据就能渲染到客户端了。
    @RequestMapping(method = GET)
    public String spittles(Model model) {
        // 当 addAttribute() 方法不指定key的时候,那么key会根据值的对象类型推断确定; 或显示指定key值。
        // 在本例中因为它是一个List<Spittle>,因此,键将会推断为spittleList
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    /* 此方法可以替换上面的 spittles() 方法

    // 该方法并没有显示指定返回的视图名称,而视图名称将会根据请求路径推断得出。
    // 因为这个方法处理针对"/spittles"的GET请求,因此视图的名称将会是spittles(去掉开头的斜线)
    @RequestMapping(method = GET)
    public List<Spittle> spittles(Model model) {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }
    */
}
```

### 接收请求参数的输入
SpringMVC 允许以多种方式将客户端数据传送到控制器的处理器方法中, 包括
- 查询参数(Query Parmeter)
- 表单参数(Form Parmeter)
- 路径变量(Path Parmeter)
```java
    // 处理查询参数 (/spittles/query?max=120&count=5)
    @RequestMapping(value = "query", method = GET)
    public String query(@RequestParam("max") long max,
                        @RequestParam(value = "count", defaultValue = "5") int count,
                        Model model) {
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    // 处理路径变量 (/spittles/path/120/5)
    // 为了实现路径变量,SpringMVC允许我们在@RequestMapping路径中添加占位符,占位符要用大括号("{"和"}")括起来;
    // 路径中的其他部分要与所处理的请求完全匹配,但是占位符部分可以是任意值
    @RequestMapping(value = "path/{max}/{count}", method = GET)
    public String path(@PathVariable("max") long max,
                       @PathVariable int count,
                       Model model) {
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    // 表单参数: SpringMVC使用 Spittle 的无参构造器创建 Spittle 对象, 并调用 Setter 方法设置对象属性。
    @RequestMapping(value = "register", method = POST)
    public String register(Spittle spittle) {
        spittleList.add(spittle);
        return "redirect:/spittles/showRegisterData";
    }
```
> InternalResourceViewResolver中提供了不仅返回视图的名称供视图解析器查找目标视图,而且返回的只还带有定向格式"redirect:"和"forward";
> 1). "redirect:" 重定向(客户端跳转), InternalResourceViewResolver看到视图格式中的 "redirect:" 前缀时,就知道要将其解析为定向的规则,而不是视图的名称。
> 2). "forward:" 请求转发(服务器端跳转), InternalResourceViewResolver当发现视图格式中以 "forward:" 作为前缀时, 请求将前往 forward 制定的URL路径。

### Validation 校验表单
- Spring3.0开始提供了对Java校验API(Java Validation API,又称 JSR-303)的支持,这些注解可以放到属性上面,从而限制这些属性的值;所有的注解都位于javax.validation.constraints包中。

 注解        | 描述
-------------|------
@AssertFalse | 所注解的元素必须是 Boolean 类型,并且值为 false
@AssertTrue  | 所注解的元素必须是 Boolean 类型,并且值为 true
@DecimalMax  | 所注解的元素必须是数字，并且它的值要小于或等于给定的 BigDecimalString 值
@DecimalMin  | 所注解的元素必须是数字，并且它的值要大于或等于给定的 BigDecimalString 值
@Digits      | 所注解的元素必须是数字，并且它的值必须有指定的位数
@Future      | 所注解的元素的值必须 是一个将来的日期
@Past        | 所注解的元素的值必须 是一个已过去的日期
@Max         | 所注解的元素必须是数字，并且它的值要小于或等于给定的值
@Min         | 所注解的元素必须是数字，并且它的值要大于或等于给定的值
@NotNull     | 所注解元素的值必须不能为 null
@Null        | 所注解元素的值必须为 null
@Pattern     | 所注解的元素的值必须匹配给定的正则表达式
@Size        | 所注解的元素的值必须是 String、集合或数组，并且它的长度要符合给定的范围

- 在Spring MVC中要使用Java校验API的话,并不需要什么额外的配置;只要保证在类路径下包含这个Java Validation API的实现即可,比如Hibernate Validation。

```xml
    <!-- 接口 -->
    <dependency>
        <groupId>javax.validation</groupId>
        <artifactId>validation-api</artifactId>
        <version>1.1.0.Final</version>
    </dependency>
    <!-- 实现 -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>5.2.4.Final</version>
    </dependency>
```
```java
    // 方法签名中的 @Valid 告知 Spring 需要确保这个对象满足校验限制, @Valid 注解的就是要校验的参数;
    // Errors 对象用于访问校验时出现的错误,必须紧跟在 @Valid 注解的参数后面。
    @RequestMapping(value = "register", method = POST)
    public String register(@Valid Spittle spittle, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spittleList.add(spittle);
        return "redirect:/spittles/showRegisterData";
    }

    public class Spittle {
        @NotNull
        @Max(Long.MAX_VALUE)
        @Min(0)
        private Long id;

        @NotNull(message = "消息不能为空")
        @Size(min = 5, max = 16, message = "消息长度不符合要求(最小长度5,最大长度16)")
        private String message;

        @NotNull
        private Date time;

        @NotNull
        @Min(0)
        private Double latitude;

        @NotNull
        @Min(0)
        private Double longitude;

        ... ...
    }
```

### AntPath Matcher

```java
public class AntPathMatcher implements PathMatcher
```

```text
PathMatcher implementation for Ant-style path patterns.
Part of this mapping code has been kindly borrowed from Apache Ant.
```

> The mapping matches URLs using the following rules:
> - ? matches one character
> - * matches zero or more characters
> - ** matches zero or more directories in a path

> ##### Examples
> - com/t?st.jsp — matches com/test.jsp but also com/tast.jsp or com/txst.jsp
> - com/*.jsp — matches all .jsp files in the com directory
> - com/**/test.jsp — matches all test.jsp files underneath the com path
> - org/springframework/**/*.jsp — matches all .jsp files underneath the org/springframework path
> - org/**/servlet/bla.jsp — matches org/springframework/servlet/bla.jsp but also org/springframework/testing/servlet/bla.jsp and org/servlet/bla.jsp

> ##### Note: 
> a pattern and a path must both be absolute or must both be relative in order for the two to match. Therefore it is recommended that users of this implementation to sanitize patterns in order to prefix them with "/" as it makes sense in the context in which they're used.
