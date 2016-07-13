package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.data.Spitter;
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
        defaultSpitter.setUsername("Guest");
        model.addAttribute(defaultSpitter);
        return "registerForm";
    }

    @RequestMapping(value = "register", method = POST)
    public String showRegistrationForm(@Valid Spitter spitter, Errors errors) {
        System.out.println(spitter);
        System.out.println(errors);

        if (errors.hasErrors()) {
            return "registerForm";
        }

        return "redirect:register";
    }

}
