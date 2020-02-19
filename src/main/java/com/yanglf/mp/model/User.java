package com.yanglf.mp.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author yanglf
 * @sine 2020.02.09
 * @descriptipon
 * @see
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model<User> {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 年龄   删除时填充
     */
    @TableField(fill = FieldFill.UPDATE)
    private Integer age;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 直属上级id
     */
    private Long managerId;
    /**
     * 创建时间   新增的时候填充
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间  更新时候填充
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 是否删除  0-否 1-是  不查询这个字段
     */
    @TableLogic
    @TableField(select = false)
    private Integer deleted;
    /**
     * 版本
     */
    @Version
    private Integer version;
}
