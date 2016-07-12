package me.caiyuan.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * YUAN
 * 7/11/16.
 */
@Controller
public class HomeController {

    // 将控制器映射到 "/", 并限定为只接受GET类型的请求
    @RequestMapping(value = "/", method = GET)
    public String home() {
        // 返回视图名称 "home"
        return "home";
    }

}
