package me.caiyuan.spring.web.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Spitter {

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
