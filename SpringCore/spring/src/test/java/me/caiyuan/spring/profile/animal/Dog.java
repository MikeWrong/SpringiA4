package me.caiyuan.spring.profile.animal;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * YUAN
 * 7/7/16.
 */
@Component
@Profile("dog")
public class Dog implements Animal {

    public void speak() {
        System.out.println("Dog : wang wang wang");
    }

}
