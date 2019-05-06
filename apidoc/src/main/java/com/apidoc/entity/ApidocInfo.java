package com.apidoc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文件基本信息
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-11
 */
@TableName("apidoc_info")
@Data
public class ApidocInfo extends Model<ApidocInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 文档标题
     */
    private String title = "文档标题";
    /**
     * 文档描述
     */
    private String description = "暂无描述";
    /**
     * 版本信息 如1.0.0
     */
    private String version = "1.0.0";
    /**
     * 包名，用于区别一个项目中的多个文档
     */
    private String packageName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ApidocInfo{" +
                ", id=" + id +
                ", title=" + title +
                ", description=" + description +
                ", version=" + version +
                ", packageName=" + packageName +
                "}";
    }
}
