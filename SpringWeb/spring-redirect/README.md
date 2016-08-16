Spring Redirect
--
对于重定向(redirect)来说, 模型(Model)不能用来传递数据。但是我们也有一些其它的方案, 能够从发起重定向的方法传递数据给处理重定向的方法 :
- 使用 URL 模板以路径变量和/或查询参数的形式传递数据
- 通过 flash 属性发送数据

`
   一般来讲,当一个处理器方法完成之后,该方法所指定的模型数据将会复制到请求中,并作为请求中的属性,请求会转发(forward)到视图上进行渲染。
   因为控制器方法和视图错处理的是同一个请求,所以在转发的过程中,请求属性能够得以保存。
`

##### 使用 URL 模板以路径变量和/或查询参数的形式传递数据

使用 _Model_ 通过路径变量和查询参数的形式跨重定向传递数据, 它只能用来发送简单的值, 如 String 和数字的值。

```java
    @RequestMapping(value="/register", method=POST)
    public String processRegistration(Spitter spitter, 
                                    Model model) {
        
        spitterRepository.save(spitter);
        
        model.addAttribute("username", spitter.getUsername());
        model.addAttribute("spitterId", spitter.getId());
        return "redirect:/spitter/{username}";
    }
    
    @RequestMapping(value="/{username}", method=GET)
    public String showSpitterProfile(@PathVariable String username, 
                                    @RequestParam String spitterId) {
        // ...
    }
```

如果 username 的属性值是 habuma 并且 spitterId 属性的值是 42, 那么结果得到的重定向 URL 路径会是 "/spitter/habuma?spitterId=42"。

##### 通过 flash 属性发送数据

使用 _RedirectAttributes_ 通过 flash 属性发送数据, 它拥有 _Model_ 的能力并且能够传递复杂的对象。

```java
    @RequestMapping(value="/register", method=POST)
    public String processRegistration(Spitter spitter,
                                    RedirectAttributes model) {
        
        spitterRepository.save(spitter);
        
        model.addFlashAttribute(spitter);
        model.addAttribute("username", spitter.getUsername());
        model.addAttribute("spitterId", spitter.getId());
        return "redirect:/spitter/{username}";
    }
    
    @RequestMapping(value="/{username}", method=GET)
    public String showSpitterProfile(Spitter spitter, 
                                    @PathVariable String username,
                                    @RequestParam String spitterId) {
        // ...
    }
```

##### 其它处理重定向后的方式 

```java
    @RequestMapping(value="/{username}", method=GET)
    public String showSpitterProfile(@PathVariable String username, Model model) {
        if (!model.containsAttribute("spitter")) {
            model.addAttribute(spitterRepository.findByUsername(username));
        }
        return "profile";
    }
```

##### Model & RedirectAttributes

```java
package org.springframework.web.servlet.mvc.support;

public interface RedirectAttributes extends Model {
    ...
}
```