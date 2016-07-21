
#### 添加其它 Servlet, Filter 和 Listener

基于Java的初始化器(initializer)的一个好处就在于我们定义任意数量的初始化器;因此,如果我们想往Web容器中注册其它组件的话,只需创建一个新的初始化器就可以了;
最简单的方法就是实现Spring的WebApplicationInitializer。

```java
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = WebPackage.class)
public class WebConfig extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
    // other ...
    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic customServlet = servletContext.addServlet("customServlet", CustomServlet.class);
        customServlet.addMapping("/customServlet");

        FilterRegistration.Dynamic customFilter = servletContext.addFilter("customFilter", CustomFilter.class);
        customFilter.addMappingForServletNames(null, false, "customServlet");
    
        servletContext.addListener(CustomListener.class);
    }
}
```
