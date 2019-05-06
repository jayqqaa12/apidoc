package com.demo.conf;

import com.apidoc.common.Const;
import com.apidoc.service.ApiDocService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

/**
 * 配置类
 * 让springboot自动扫描并管理apidoc工具下的所有class
 */
@Configuration
@EnableTransactionManagement//启动事务
@ComponentScan("com.apidoc")//扫描组件类
@MapperScan("com.apidoc.dao")//扫描数据库操作层的类
@EntityScan("com.apidoc.entity")//扫描实体类
public class ApiDocConfig {
    @Bean
    public ApiDocService generator() {
        //所有的常量都在Const类下，需要修改常量的在这里配置即可
        //配置代码的绝对路径，方便扫描代码的注释，因为注释编译之后就被jvm剔除了，只能扫描源码，不配置则不扫描注释
        //默认路径为{项目路径}+src/main/java
        Const.codePath = Const.projectPath + "apidoc-demo" + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        Const.baseUrl="http://localhost:8080";

        Const.header.put("Authorization","eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWQiOjEsImV4cCI6MTU0MjE3ODMyN30.if6w0ePkg6gGzGg4Qm90WOAq09Nep4xLYQuVnRSdk_cBStXyU2lytnELHZrdmgBM_0PXJtXiJvY8Y_pOObeQbiYwc-PoN4XCs5Aey5OQ4FyLiyspqLA8Sxu_aCds4kp88rrd6Gzcupjndm41UX2pL-WvU1MnlXaEDC6h-llpVx0");
        Const.header.put("platform","doc");
        Const.header.put("version","1");
        Const.header.put("channel","doc");
        Const.header.put("timestamp",System.currentTimeMillis()+"");

        return new ApiDocService();
    }
}
