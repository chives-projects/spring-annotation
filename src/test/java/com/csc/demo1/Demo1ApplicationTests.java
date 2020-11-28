package com.csc.demo1;

import com.csc.demo1.controller.DemoController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo1ApplicationTests {

    @Autowired
    DemoController demoController;

    @Test
    public void contextLoads() {
        System.out.println(demoController.get("1"));
        System.out.println("hello");
    }

}
