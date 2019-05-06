package com.demo.controller;

import com.apidoc.annotation.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试一个模块由多个类组成
 */
@Api("测试一个模块由多个类组成")
@RestController
@RequestMapping("/testModules1")
public class TesModules1Controller {

    /**
     *
     * 接口测试
     * @param a 参数1
     * @param b 参数2
     *
     * @desc 描述的内容
     *
     */
    @GetMapping(value = "/action2")
    public void action3(String  a ,String b) {


    }


}
