package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.bean.Spitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
            @RequestParam("redirect") int redirect,
            Spitter spitter,
            RedirectAttributes model) {

        if (redirect == 1) {
            Model _model = model;
            _model.addAttribute("username", spitter.getName());
            return "redirect:/redirect1";
        } else {
            model.addFlashAttribute(spitter);
            model.addAttribute("username", spitter.getName());
            return "redirect:/redirect2";
        }
    }

    @ResponseBody
    @RequestMapping("redirect1")
    public Object redirect1(@RequestParam String username) {
        return username;
    }

    @ResponseBody
    @RequestMapping("redirect2")
    public Object redirect2(Spitter spitter, @RequestParam String username) {
        return spitter;
    }

}
