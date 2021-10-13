package com.csc.demo1.controller;

import com.csc.demo1.po.User;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @PackageName: com.csc.gatewaybench.gateway.controller
 * @Author: csc
 * @Create: 2020-08-04 16:09
 * @Version: 1.0
 */
@RestController
@RequestMapping("gateway")
public class DemoController {

    //    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @GetMapping(value = "get/getget")
    public String get() {
        LoggerFactory.getLogger(this.getClass()).info("---------info");
        return "spring cloud gateway get";
    }


    @GetMapping(value = "get/{id}")
    public String getPath(@PathVariable String id) {
        return "spring cloud gateway get PathVariable " + id;
    }

    @GetMapping(value = "get")
    public String get(String id) {
        return "spring cloud gateway get " + id;
    }

    @GetMapping(value = "getUser")
    public User getUser(@Validated @RequestBody User user) {
        user.setId("getUser");
        return user;
    }

    @PostMapping(value = "post/{id}")
    public String post(@PathVariable("id") String id) {
        return "spring cloud gateway post " + id;
    }

    @PostMapping(value = "postUser")
    public User postUser(@Validated @RequestBody User user) {
        user.setId("postUser");
        return user;
    }

    @GetMapping(value = "error")
    public String error() {
        throw new NullPointerException("error");
    }

    @GetMapping(value = "timeout/{time}")
    public String timeout(@PathVariable int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "spring cloud gateway timeout";
    }

}
