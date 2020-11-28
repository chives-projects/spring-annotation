package com.csc.demo1.po;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Description:
 * @PackageName: com.csc.demo1.po
 * @Author: csc
 * @Create: 2020-09-25 17:20
 * @Version: 1.0
 */
public class User {
    @NotEmpty
    private String id;

    @Valid
    List<Item> items;
    @Min(value = 2,message = "小于2")
    private int age;
    @Max(11)
    private int high;
    @Email(message = "email")
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
