package com.apidoc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * 文档模块信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-13
 */
@TableName("apidoc_module")
@Data
public class ApidocModule extends Model<ApidocModule> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 模块名称
     */
    private String name;
    /**
     * 排序
     */
    private Integer seq;
    /**
     * 包名，区分不用的文档
     */
    private String packageName;
    /**
     * 类全名，多个之间用英文逗号隔开
     */
    private String classList;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    
    /**
     * 重写equals和hashCode算法 只要name相同则认为两个对象相同
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApidocModule)) return false;
        ApidocModule that = (ApidocModule) o;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
