package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.repository.Spittle;
import me.caiyuan.spring.web.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * YUAN
 * 7/12/16.
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository spittleRepository;

    @Autowired
    public SpittleController(SpittleRepository spittleRepository) {
        this.spittleRepository = spittleRepository;
    }

    /* 此方法可以替换上面的 spittles() 方法

    // 该方法并没有显示指定返回的视图名称,而视图名称将会根据请求路径推断得出。
    // 因为这个方法处理针对"/spittles"的GET请求,因此视图的名称将会是spittles(去掉开头的斜线)
    @RequestMapping(method = GET)
    public List<Spittle> spittles(Model model) {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }
    */

    // 在方法签名中给定了一个Model作为参数,这样就能将Repository中获取的数据填充到模型中;
    // Model 实际上是一个Map,它会传递给视图,这样数据就能渲染到客户端了。
    @RequestMapping(method = GET)
    public String spittles(Model model) {
        // 当 addAttribute() 方法不指定key的时候,那么key会根据值的对象类型推断确定; 或显示指定key值。
        // 在本例中因为它是一个List<Spittle>,因此,键将会推断为spittleList
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    // 处理查询参数 (/spittles/query?max=120&count=5)
    @RequestMapping(value = "query", method = GET)
    public String query(@RequestParam("max") long max,
                        @RequestParam(value = "count", defaultValue = "5") int count,
                        Model model) {
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    // 处理路径变量 (/spittles/path/120/5)
    // 为了实现路径变量,SpringMVC允许我们在@RequestMapping路径中添加占位符,占位符要用大括号("{"和"}")括起来;
    // 路径中的其他部分要与所处理的请求完全匹配,但是占位符部分可以是任意值
    @RequestMapping(value = "path/{max}/{count}", method = GET)
    public String path(@PathVariable("max") long max,
                       @PathVariable int count,
                       Model model) {
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    private List<Spittle> spittleList = new ArrayList<>();

    @RequestMapping(value = "register", method = GET)
    public String showRegisterForm() {
        return "registerForm";
    }

    // 方法签名中的 @Valid 告知 Spring 需要确保这个对象满足校验限制; @Valid 注解的就是要校验的参数。
    // Errors 对象用于访问校验时出现的错误,必须紧跟在 @Valid 注解的参数后面。
    @RequestMapping(value = "register", method = POST)
    public String register(@Valid Spittle spittle, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spittleList.add(spittle);
        return "redirect:/spittles/showRegisterData";
    }

    @RequestMapping(value = "showRegisterData", method = GET)
    public String showRegisterData(Model model) {
        model.addAttribute(spittleList);
        return "spittles";
    }

}
