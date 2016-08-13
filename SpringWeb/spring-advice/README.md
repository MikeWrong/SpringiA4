Spring ControllerAdvice
--
Spring 3.2 引入了控制器通知, 控制器通知(Controller advice)是任意带有 @ControllerAdvice 注解的类, 这个类会包含一个或多个如下类型的方法:
- @ExceptionHandler 注解标记的方法
- @InitBinder 注解标注的方法
- @ModelAttribute 注解标注的方法

带有 @ControllerAdvice 注解的类中, 以上所述的这些方法会运用到整个应用程序所有控制器中带有 @RequestMapping 注解的方法上。
@ControllerAdvice 注解本身已经使用了 @Component, 因此 @ControllerAdvice 注解所标注的类将会自动被组件扫描获取到。

```java
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exceptionHandler(RuntimeException ex, HttpServletRequest request) {
       
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception", ex);
       
        System.out.println(request);
       
        return modelAndView;
    }

}
```
