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

##### CommonsMultipartResolver

```java
// WebConfig.java
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));
        return multipartResolver;
    }
```

##### StandardServletMultipartResolver

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
