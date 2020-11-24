package com.csc.demo1.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-11-24 16:51
 * @Version: 1.0
 */
@Component
public class PostConstructTest {
    public PostConstructTest() {
        System.out.println("PostConstructTest constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("[PostConstruct] PostConstructTest");
    }
}
