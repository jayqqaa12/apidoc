package com.apidoc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 文档参数信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-15
 */
@TableName("apidoc_param")
@Data
public class ApidocParam extends Model<ApidocParam> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父参数id
     */
    private Integer pid;
    /**
     * 名称
     */
    private String name;
    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 描述
     */
    private String description;
    /**
     * 默认值
     */
    private String defaultValue;
    /**
     * 是否必须
     */
    @JSONField(serializeUsing=ToStringSerializer.class)
    private Boolean required;
    /**
     * 是否是返回值
     */
    private Boolean returnd;
    /**
     * 接口id
     */
    private Integer actionId;
    /**
     * 所属类名 父类名
     */
    private String pclassName;

    private List<ApidocParam> list;

    
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
