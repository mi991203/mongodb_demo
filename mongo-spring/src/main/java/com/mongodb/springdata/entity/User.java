package com.mongodb.springdata.entity;

import java.io.Serializable;

/**
 * @author : SH35856
 * @Description: TODO
 * @date: 2022/6/23 18:54
 */
public class User implements Serializable {
    private static final Long serialVersionUid = -1L;

    private Long id;

    private String name;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
