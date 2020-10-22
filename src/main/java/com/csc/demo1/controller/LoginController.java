package com.csc.demo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @PackageName: com.csc.demo1.controller
 * @Author: CSC
 * @Create: 2020-07-09 16:35
 * @Version: 1.0
 */
@RequestMapping(value = "login")
@RestController
public class LoginController {
    @GetMapping(value = "log")
    public String login(){
        System.out.println("666");
        return "666";
    }

}
