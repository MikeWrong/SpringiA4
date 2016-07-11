Spring Web
--

#### 跟踪 Spring MVC 的请求
![Dispatcher](dispatcher.png)

1. Request : 带有用户请求的信息, 至少会包含请求的 URL。

2. DispatcherServlet : SpringMVC 与大多数 Java 的 Web 框架一样, 所有的请求都会经过一个前端控制器 Servlet;
前端控制器是常用的 Web 应用程序模式, 在这里单例的 Servlet 将请求委托给应用程序的其他组件来执行实际的处理。
在 SpringMVC 中 DispatcherServlet 就是前端控制器。

3. HandlerMapping :

4. Controller :

5. Model & Logical view name :

6. ViewResolver :

7. View :

8. Response :
