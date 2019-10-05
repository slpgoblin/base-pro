package com.goblin.code.controller;

import cn.hutool.json.JSONUtil;
import com.goblin.code.api.HelloService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @program: base-code
 * @description: controller用例
 * @author: Guojs
 * @create: 2019-10-04 00:35
 **/
@RestController
@RequestMapping("test")
public class HelloController {

    @Resource
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello(String name){
        return "hello " + (StringUtils.isNotBlank(name) ? name : "goblin");
    }

    @GetMapping("/data")
    public String data(String id){
        if (StringUtils.isNotBlank(id)){
            return JSONUtil.toJsonStr(helloService.listTestData(id));
        }else {
            return "123";
        }
    }
}
