package com.apidoc.entity.bean;

import com.apidoc.entity.ApidocParam;
import lombok.Data;

import java.util.List;

/**
 * 参数 请求参数和响应参数
 */
@Data
public class Params {
    private String type;//参数的请求或响应类型
    private String description;//描述
    private List<ApidocParam> params; //参数集合


}
