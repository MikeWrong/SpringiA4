package me.caiyuan.spring.base.pkg2;

import me.caiyuan.spring.base.pkg1.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * YUAN
 * 7/8/16.
 */
@Component
public class Obj {

    @Autowired
    public A a;

}
