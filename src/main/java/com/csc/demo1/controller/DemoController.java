package com.csc.demo1.controller;

import com.csc.demo1.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @PackageName: com.csc.gatewaybench.gateway.controller
 * @Author: csc
 * @Create: 2020-08-04 16:09
 * @Version: 1.0
 */
@Api(tags = "error")
@RestController
@RequestMapping("gateway")
public class DemoController {

    @GetMapping(value = "get/getget")
    public String get() {
        return "spring cloud gateway get";
    }

    @GetMapping(value = "get/{id}")
    public String getPath(@PathVariable String id) {
        return "spring cloud gateway get " + id;
    }

    @GetMapping(value = "get")
    public String get(String id) {
        return "spring cloud gateway get " + id;
    }

    @GetMapping(value = "getUser")
    public User getUser(@Validated @RequestBody User user) {
        user.setId("new");
        return user;
    }

    @PostMapping(value = "post/{id}")
    public String post(@PathVariable("id") String id) {
        return "spring cloud gateway post " + id;
    }

    @ApiOperation("get")
    @PostMapping(value = "postUser")
    public User postUser(@Validated @RequestBody User user) {
        user.setId("new");
        return user;
    }

    @GetMapping(value = "error")
    public String error() {
        throw new NullPointerException("dd");
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
