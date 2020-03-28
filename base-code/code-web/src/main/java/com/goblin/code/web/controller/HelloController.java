package com.goblin.code.web.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.goblin.code.api.HelloService;
import com.goblin.code.dto.TestDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

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

    @GetMapping(value = "/data")
    public String data(TestDTO testDTO){
        if (ObjectUtil.isNotEmpty(testDTO)){
            return JSONUtil.toJsonStr(helloService.listTestData(testDTO));
        }else {
            return "123";
        }
    }
}
