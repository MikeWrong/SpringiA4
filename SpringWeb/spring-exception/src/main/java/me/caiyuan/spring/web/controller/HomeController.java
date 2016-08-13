package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.bean.Spitter;
import me.caiyuan.spring.web.exception.PictureNotFoundException;
import me.caiyuan.spring.web.exception.SpittleUndefineException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

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

        if (picture.length == 0) {
            throw new PictureNotFoundException();
        }

        if (Objects.isNull(spitter.getName()) || Objects.isNull(spitter.getBirthday())) {
            throw new SpittleUndefineException();
        }

        return spitter;
    }

    @ExceptionHandler(PictureNotFoundException.class)
    public ModelAndView handleNotFound(RuntimeException ex) {

        ModelAndView model = new ModelAndView("error");
        model.addObject("exception", ex);

        return model;
    }

}
