package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.data.Spitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * YUAN
 * 7/13/16.
 */
@Controller
public class SpitterController {

    @RequestMapping(value = "register", method = GET)
    public String showRegistrationForm(Model model) {
        Spitter defaultSpitter = new Spitter();
        // 设置表单的默认值
        defaultSpitter.setUsername("Guest");
        // 在模型中必须有一个key为spitter的对象,否则的话,表单不能正常渲染(会出现JSP错误 java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'spitter' available as request attribute)
        model.addAttribute(defaultSpitter);
        return "registerForm";
    }

    @RequestMapping(value = "register", method = POST)
    public String showRegistrationForm(@Valid Spitter spitter, Errors errors) {
        // 校验错误处理
        if (errors.hasErrors()) {
            return "registerForm";
        }
        // 保存 Spitter
        // ...
        return "redirect:register";
    }

    @Autowired
    public void test(Object numberList) {
        System.out.println(numberList);
    }

}
