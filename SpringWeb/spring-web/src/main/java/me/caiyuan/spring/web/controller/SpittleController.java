package me.caiyuan.spring.web.controller;

import me.caiyuan.spring.web.repository.SpittleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    // 在方法签名中给定了一个Model作为参数,这样就能将Repository中获取的数据填充到模型中;
    // Model 实际上是一个Map,它会传递给视图,这样数据就能渲染到客户端了。
    @RequestMapping(method = GET)
    public String spittles(Model model) {
        // 当 addAttribute() 方法不指定key的时候,那么key会根据值的对象类型推断确定; 或显示指定key值。
        // 在本例中因为它是一个List<Spittle>,因此,键将会推断为spittleList
        model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
        return "spittles";
    }

    /* 此方法可以替换上面的 spittles() 方法

    // 该方法并没有显示指定返回的视图名称,而视图名称将会根据请求路径推断得出。
    // 因为这个方法处理针对"/spittles"的GET请求,因此视图的名称将会是spittles(去掉开头的斜线)
    @RequestMapping(method = GET)
    public List<Spittle> spittles(Model model) {
        return spittleRepository.findSpittles(Long.MAX_VALUE, 20);
    }
    */

    // spittles/query?max=120&count=5
    @RequestMapping(value = "query", method = GET)
    public String spittles(@RequestParam("max") long max,
                           @RequestParam("count") int count,
                           Model model) {
        model.addAttribute(spittleRepository.findSpittles(max, count));
        return "spittles";
    }

}
