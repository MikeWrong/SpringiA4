package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.bean.Spitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
            Errors errors,
            @RequestParam("redirect") int redirect,
            Model model1,
            RedirectAttributes model2) {

        if (errors.hasErrors()) {
            System.out.println(errors);
        }

        System.out.println(picture.length);
        System.out.println(spitter);

        if (redirect == 1) {
            model1.addAttribute(spitter);
            model1.addAttribute("redirect", redirect);
            return "redirect:/redirect1";
        } else {
            model2.addFlashAttribute(spitter);
            model2.addFlashAttribute("redirect", redirect);
            return "redirect:/redirect2";
        }
    }

    @ResponseBody
    @RequestMapping("redirect1")
    public Object redirect1(Spitter spitter) {
        return spitter;
    }

    @ResponseBody
    @RequestMapping("redirect2")
    public Object redirect2(Spitter spitter) {
        return spitter;
    }

}
