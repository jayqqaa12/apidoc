package com.apidoc.entity.bean;


import lombok.Data;

/**
 * 接口详情 bean
 */
@Data
public class Detail {

    private Integer id;
    private String mapping;//接口地址映射 url
    private String requestMethod;//请求方式
    private String description = ""; //接口描述
    private Params requestParam;//请求参数
    private Params responseParam;//响应参数参数
 
}
