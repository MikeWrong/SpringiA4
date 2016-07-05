package me.caiyuan.spring.aspect.encoreable;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * YUAN
 * 6/30/16.
 */
@Component
@Aspect
public class EncoreableIntroducer {

    // 通过 @DeclareParents 注解，将 Encoreable 接口引入到 Performance bean 中。
    @DeclareParents(value = "me.caiyuan.spring.aspect.concert.Performance+", defaultImpl = DefaultEncoreable.class)
    public Encoreable encoreable;

}
