package com.apidoc.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 文档接口信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-14
 */
@TableName("apidoc_action")
@Data
public class ApidocAction extends Model<ApidocAction> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 模块id
     */
    private Integer moduleId;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 方法的唯一标示符，方法名-形参类型,形参类型
     * 为了区别java方法的重载
     */
    private String methoduuid;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 请求参数描述
     */
    private String requestDescription;

    /**
     * 响应参数描述
     */
    private String responseDescription;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    
    /**
     * 重写equals和hashCode算法，只要methoduuid一致则认为两个action对象相同
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApidocAction)) return false;
        ApidocAction that = (ApidocAction) o;
        return Objects.equals(getMethoduuid(), that.getMethoduuid());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMethoduuid());
    }
}
