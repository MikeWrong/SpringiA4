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

##### StandardServletMultipartResolver

