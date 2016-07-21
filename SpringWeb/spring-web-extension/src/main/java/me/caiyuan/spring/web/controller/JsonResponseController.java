package me.caiyuan.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * YUAN
 * 7/22/16.
 */
@Controller
public class JsonResponseController {

    @ResponseBody
    @RequestMapping("json")
    public Object json() {
        return Arrays.asList("value1", "value2", "value3", "value4", "value5");
    }

}
