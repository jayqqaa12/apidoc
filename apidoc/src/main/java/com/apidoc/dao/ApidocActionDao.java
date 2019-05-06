package com.apidoc.dao;

import com.apidoc.entity.ApidocAction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 文档接口信息 Mapper 接口
 * </p>
 *
 * @author 此代码为自动生成
 * @since 2018-09-14
 */
public interface ApidocActionDao extends BaseMapper<ApidocAction> {

    /**
     * 通过模块id查询接口列表
     *
     * @param moduleId
     * @return
     */
    @Select("select id,`name`,module_id,`seq`,methoduuid from apidoc_action where module_id=#{moduleId}")
    List<ApidocAction> selectByModuleId(@Param("moduleId") Integer moduleId);


    /**
     * 通过id查询接口描述
     *
     * @param id
     * @return
     */
    @Select("select description from apidoc_action where id = #{id}")
    String selectDescriptionById(@Param("id") Integer id);

    /**
     * 通过id查询接口请求参数的描述
     *
     * @param id
     * @return
     */
    @Select("select request_description from apidoc_action where id = #{id}")
    String selectRequestDescriptionById(@Param("id") Integer id);

    /**
     * 通过id查询接口响应参数的描述
     *
     * @param id
     * @return
     */
    @Select("select response_description from apidoc_action where id = #{id}")
    String selectResponseDescriptionById(@Param("id") Integer id);
}
