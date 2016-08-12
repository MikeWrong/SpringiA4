package me.caiyuan.spring.web.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Spitter {

    private String picture;
    private String name;
    @DateTimeFormat
    private Date birthday;

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

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
