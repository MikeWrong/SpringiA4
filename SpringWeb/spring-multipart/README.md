Multipart
--

> 如上传图片时, multipart 格式的数据会将一个表单拆分为多个部分(part), 每个部分对应一个输入域;
> 在一般的表单输入域中, 它所对应的部分中会放置文本类型数据, 但如果上传文件的话, 它所对应的部分可以是二进制。
> 下面展示了 multipart 的请求体: 

```text
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="firstName"
Charles
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="lastName"
Xavier
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="email"
charles@xmen.com
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="username"
professorx
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="password"
letmein01
------WebKitFormBoundaryqgkaBn8IHJCuNmiW
Content-Disposition: form-data; name="profilePicture"; filename="me.jpg"
Content-Type: image/jpeg
  [[ Binary image data goes here ]]
------WebKitFormBoundaryqgkaBn8IHJCuNmiW--
```

#### 配置 multipart 解析器

Spring 提供了内置的两个 MultipartResolver 的实现供我们选择:
- CommonsMultipartResolver : 使用 Jakarta Commons FileUpload 解析 multipart 请求
- StandardServletMultipartResolver 依赖于 Servlet 3.0 对 multipart 请求的支持 (始于 Spring 3.1, 推荐使用)

##### CommonsMultipartResolver

如果需要将应用部署到非 Servlet 3.0 容器中,那么需要使用 CommonsMultipartResolver; 或者可以自己编写 MultipartResolver 的实现, 除非想要处理 multipart 请走的时候执行特定的逻辑, 否则没有必要。
CommonsMultipartResolver 不会强制要求设置零时文件路径, 默认情况下, 这个路径就是 Servlet 容器的临时目录, 我们也可以指定一个不同的位置并且设置其它的更多参数。

```java
// WebConfig.java
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));
        // multipartResolver.setXXX
        return multipartResolver;
    }
```

##### StandardServletMultipartResolver

StandardServletMultipartResolver 需要设置文件上传的临时目录, 否则无法正常工作; 
具体来向需要在 web.xml 或 Servlet 初始化类中, 将 multipart 的具体细节作为 DispatcherServlet 配置的一部分。

- 如果我们采用Servlet初始化类的方法来配置DispatcherServlet的话, 这个初始化类应该已经实现了WebApplicationInitializer,
  那我们可以在Servlet registration上调用setMultipartConfig()方法, 传入一个MultipartConfigElement实例。
  如下最基本的DispatcherServlet multipart 配置:

```java
  DispatcherServlet ds = new DispatcherServlet();
  Dynamic registration = context.addServlet("appServlet", ds);
  registration.addMapping("/");
  registration.setMultipartConfig(
        new MultipartConfigElement("/tmp")    
    );
```

- 如果我们配置的 DispatcherServlet 的 Servlet 初始化类继承了 AbstractAnnotationConfigDispatcherServletInitializer 或 AbstractDispatcherServletInitializer 的话,
  那么我们不会直接创建 DispatcherServlet 实例并将其注册到 Servlet 上下文中。但是我们可以重载 customizeRegistration(Dynamic registration) 方法类配置 multipart 的具体细节:

```java
  @Override
  protected void customizeRegistration(ServletRegistration.Dynamic registration) {
      registration.setMultipartConfig(new MultipartConfigElement("/tmp", 2097152, 4194304, 0));
  }
```

_完整配置如下_

```java
// WebConfig.java
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }
    
// WebAppInitializer.java
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("/tmp", 2097152, 4194304, 0));
    }
```

#### 使用 multipart 解析器

```java
// HomeController.java
    @ResponseBody
    @RequestMapping(value = "/registration", method = POST)
    public String registration(
            @RequestPart("picture") byte[] picture,
            HttpServletRequest request) {

        // FileCopyUtils.copy(picture, out);

        if (request instanceof MultipartRequest) {
            //
            // MultipartFile.transferTo(File dest)
        }

        return "ok";
    }
```

```html
    <form method="post" enctype="multipart/form-data" action="/registration">
        <label>Picture:
            <input name="picture" type="file" accept="image/jpeg,image/png,image/gif">
        </label>
        <button type="submit">Submit</button>
    </form>
```

#### javax.servlet.http.Part

如果需要将应用程序部署到 Servlet 3.0 中, 那么会有一个 MultipartFile 的替代方案。Spring MVC 也能接受 javax.servlet.http.Part 作为控制器方法的参数。配置及使用如下:

```java
// WebAppInitializer.java
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("/tmp", 2097152, 4194304, 0));
    }
    
// HomeController.java
    @ResponseBody
    @RequestMapping(value = "/registration", method = POST)
    public String registration(
            @RequestPart("picture") Part picture,
            HttpServletRequest request) {

        if (request instanceof MultipartRequest) {
            //
            // MultipartFile.transferTo(File dest)
        }

        return "ok";
    }
```
```html
    <form method="post" enctype="multipart/form-data" action="/registration">
        <label>Picture:
            <input name="picture" type="file" accept="image/jpeg,image/png,image/gif">
        </label>
        <button type="submit">Submit</button>
    </form>
```
