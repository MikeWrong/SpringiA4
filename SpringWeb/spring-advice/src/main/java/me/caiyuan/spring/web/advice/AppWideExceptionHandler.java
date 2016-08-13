package me.caiyuan.spring.web.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * YUAN
 * 8/13/16
 */
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
