Spring Exception
--
不管发生什么事情, 不管是好是坏, Servlet 请求的输出都是一个 Servlet 响应。 如果请求处理的时候, 出现了异常, 那么它输出的依然会是 Servlet 响应; 异常必须要以某种方式转换为响应。

Spring 提供了多种方式将异常转换为响应:
- 特定的 Spring 异常将会自动转换为指定的 Http 状态码
- 异常上可以添加 @ResponseStatus 注解, 从而将其映射为某一个 Http 状态码
- 在方法上可以添加 @ExceptionHandler 注解, 使其用来处理异常

#### Spring 的一些异常会默认映射为 Http 状态码
 Spring exception | HTTP status code
-----------------------------------------|-------------------------
BindException                            |400 - Bad Request
ConversionNotSupportedException          |500 - Internal Server Error 
HttpMediaTypeNotAcceptableException      |406 - Not Acceptable
HttpMediaTypeNotSupportedException       |415 - Unsupported Media Type 
HttpMessageNotReadableException          |400 - Bad Request
HttpMessageNotWritableException          |500 - Internal Server Error 
HttpRequestMethodNotSupportedException   |405 - Method Not Allowed
MethodArgumentNotValidException          |400 - Bad Request
MissingServletRequestParameterException  |400 - Bad Request
MissingServletRequestPartException       |400 - Bad Request
NoSuchRequestHandlingMethodException     |404 - Not Found
TypeMismatchException                    |400 - Bad Request
  表中的异常一般会由Spring自身抛出, 作为 DispatcherServlet 处理过程中或进行校验时出现问题的结果。
  例如, 如果 DispatcherServlet 无法找到合适处理请求的控制器方法, 那么会抛出 NoSuchRequestHandlingMethodException 异常, 最终结果就是产生 404 状态码的响应 (Not Found)。
  
#### @ResponseStatus 将异常映射为状态码
```java
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,
        reason = "Spittle Undefine")
public class SpittleUndefineException extends RuntimeException {
}
```

#### @ExceptionHandler 编写异常的处理方法 (将异常转换为一个普通的 Servlet 响应)
```java
// HomeController.java
// 仅只处理 HomeController 控制器或其子类控制器中的 SpittleUndefineException 异常

    @ExceptionHandler(SpittleUndefineException.class)
    public ModelAndView handleNotFound(RuntimeException ex) {

        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);

        return model;
    }
```

_异常处理的其它方式_

    @See Spring ControllerAdvice


*更多 @ResponseStatus 和 @ExceptionHandler 的使用方式见 API 文档。*







