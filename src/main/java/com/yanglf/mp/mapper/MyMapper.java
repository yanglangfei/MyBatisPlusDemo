package com.yanglf.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yanglf
 * @sine 2020.02.10
 * @descriptipon
 * @see
 */
public interface MyMapper<T> extends BaseMapper<T> {

    /**
     * 删除所有
     *
     * @return 影响行数
     */
    Integer deleteAll();

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatchSomeColumn(List<T> list);


    /**
     * 逻辑删除时  填充某个字段
     * @param entity
     * @return
     */
    int deleteByIdWithFill(T entity);


    /**
     *  更新时  不更新 name 字段
     * @param entity
     * @return
     */
    int alwaysUpdateSomeColumnById(@Param(Constants.ENTITY) T entity);

}
