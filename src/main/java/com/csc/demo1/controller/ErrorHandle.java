package com.csc.demo1.controller;

import com.csc.demo1.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
public class ErrorHandle {

    @GetMapping(value = "get/getget")
    public String get() {
        System.out.println("9520");
        return "springcloud gateway get";
    }
    @GetMapping(value = "error")
    public String error() {
        throw new NullPointerException("dd");
    }

    @PostMapping(value = "post/{id}")
    public String post1(@PathVariable("id") String id) {
        return "springcloud gateway post " + id;
    }

    @ApiOperation("get")
    @PostMapping(value = "get")
    public String getget(@Validated @RequestBody User user) {
        System.out.println("9520");
        return "springcloud gateway get";
    }

}
