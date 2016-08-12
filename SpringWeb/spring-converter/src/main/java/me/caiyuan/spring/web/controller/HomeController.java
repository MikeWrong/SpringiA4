package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.bean.Spitter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * YUAN
 * 7/11/16.
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/", method = GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/registration", method = POST)
    public String registration(
            @RequestPart("picture") byte[] picture,
            Spitter spitter,
            Errors errors) {

        System.out.println(picture);

        return "home";
    }

}
