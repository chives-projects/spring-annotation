package com.csc.demo1.po;

import javax.validation.constraints.NotEmpty;

/**
 * @Description:
 * @PackageName: com.csc.demo1.po
 * @Author: csc
 * @Create: 2020-09-25 17:36
 * @Version: 1.0
 */
public class Item {
    @NotEmpty
    private String id;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
