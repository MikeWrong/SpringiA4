### ViewResolver
Spring MVC 定义了一个名为 ViewResolver 的接口, 它大致如下所示:
```java
public interface ViewResolver {
    View resolveViewName(String viewName, Locale locale) throws Exception;
}
```
当给 resolveViewName() 方法传入一个视图名称和 Locale 对象时, 它会返回一个 View 实例。 View 是另外一个接口, 如下所示:
```java
public interface View {
    String getContentType();
    void render(Map<String, ?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
```
View 接口的任务就是接受模型以及Servlet的request和response对象,并将输出结果渲染到response中。

- Spring提供了多个内置的实现

  视图解析器                     |  描述
--------------------------------|----------
BeanNameViewResolver            | 将视图解析为Spring应用上下文中的Bean,其中Bean的ID与视图的名字相同
ContentNegotiatingViewResolver  | 通过考虑客户端需要的内容类型来解析视图,委托给另一个能够产生对应内容类型的视图解析器
FreeMarkerViewResolver          | 将试图解析为FreeMarker模板
InternalResourceViewResolver    | 将视图解析为Web应用的内部资源(一般为JSP)
JasperReportsViewResolver       | 将试图解析为JasperReports定义
ResourceBundleViewResolver      | 将试图解析为资源bundle(一般为属性文件)
TilesViewResolver               | 将试图解析为Apache Tile定义,其中tile ID与视图名称相同。注意有两个不同的TilesViewResolver实现,分别对应Tiles2和Tiles3
UrlBasedViewResolver            | 直接根据视图的名称解析视图,视图的名称会匹配一个物理视图的定义
VelocityLayoutViewResolver      | 将视图解析为Velocity布局,从不同的Velocity模板中组合页面
VelocityViewResolver            | 将视图解析为Velocity模板
XmlViewResolver                 | 将试图解析为特定XML文件中的Bean定义,类似于BeanNameViewResolver
XsltViewResolver                | 将试图解析为XSLT转换后的结果

### 创建JSP视图

Spring提供了两种支持JSP视图的方式:
> - InternalResourceViewResolver会将试图名称解析为JSP文件。另外,如果在理的JSP页面中使用了JSP标签库(JSTL)的话,
>   InternalResourceViewResolver能够将视图名解析为JstlView形式的JSP文件,从而将JSTL本地化和资源bundle变量暴露给JSTL的格式化(formatting)和信息(message)标签。
> - Spring提供了两个JSP标签库,一个用于表单到模型的绑定,另一个提供了通用的工具类特性。

1). 配置适用于JSP的视图解析器
```text
InternalResourceViewResolver 最终会将逻辑视图解析为 InternalResourceView 实例,这个实例会引用JSP文件。
```
```java
    @Bean
    public ViewResolver viewResolver() {
        IntenalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
```
或
```xml
<bean id="viewResolver"
    class="org.springframework.web.servlet.view.InternalResourceViewResolver"
    p:prefix="/WEB-INF/views/"
    p:suffix=".jsp"
    />
```
InternalResourceViewResolver配置就绪之后,它就会将逻辑视图解析为JSP文件,如下所示:
> home 将会解析为 "/WEB-INF/views/home.jsp"

2). 解析JSTL视图
```text
如果JSP使用JSTL标签来处理格式化和信息的话,那么我们希望InternalResourceViewResolver将试图解析为JstlView;
JSTL 的格式化标签需要一个 Locale 对象,以便于恰当的格式化地域相关的值,如日期和货币;
通过解析JstlView,JSTL能够获得Locale对象以及Spring中的配置的信息资源。
```
```java
    @Bean
    public ViewResolver viewResolver() {
        IntenalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
        return resolver;
    }
```
或
```xml
<bean id="viewResolver"
    class="org.springframework.web.servlet.view.InternalResourceViewResolver"
    p:prefix="/WEB-INF/views/"
    p:suffix=".jsp"
    p:viewClass="org.springframework.web.servlet.view.JstlView"
    />
```
#### 将表单绑定到模型上
```text
Spring的表单绑定JSP标签库包含了14个标签,它会绑定模型中的一个对象,能够根据模型中对象的属性填充值;
标签库中还包含了一个为用户展现错误的标签,它会将错误信息渲染到最终的HTML之中。
```
为了使用表单绑定库,需要在JSP页面中对其进行声明:
```jsp
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
```




