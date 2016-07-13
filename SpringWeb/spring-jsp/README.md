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

