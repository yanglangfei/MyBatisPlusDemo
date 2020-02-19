package com.yanglf.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.yanglf.mp.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author yanglf
 * @sine 2020.02.09
 * @descriptipon
 * @see
 */
public interface UserMapper extends MyMapper<User> {


    /**
     * @param wrapper
     * @return
     */
    @Select("select * from user ${ew.customSqlSegment}")
    List<User> selectAll(@Param(value = Constants.WRAPPER) Wrapper<User> wrapper);

}
