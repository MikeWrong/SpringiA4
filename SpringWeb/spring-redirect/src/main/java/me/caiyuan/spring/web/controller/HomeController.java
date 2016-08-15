package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.bean.Spitter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @ResponseBody
    @RequestMapping(value = "/registration", method = POST)
    public Object registration(
            @RequestPart("picture") byte[] picture,
            Spitter spitter,
            Errors errors) {

        if (errors.hasErrors()) {
            System.out.println(errors);
        }

        System.out.println(picture.length);
        System.out.println(spitter);

        return spitter;
    }

}
