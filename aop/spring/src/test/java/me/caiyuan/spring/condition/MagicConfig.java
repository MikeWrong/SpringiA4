package me.caiyuan.spring.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MagicConfig {

    // @Conditional 条件化创建 bean; 在这里如果 MagicExistsCondition.matches() 返回 true 则创建 magicBean

    @Bean
    @Conditional(MagicExistsCondition.class)
    public MagicBean magicBean() {
        return new MagicBean();
    }

}