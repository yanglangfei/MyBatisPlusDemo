package com.yanglf.mp.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author yanglf
 * @sine 2020.02.10
 * @descriptipon
 * @see
 */
@Component
@Slf4j
public class MyMetaObjectHandle implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert-----------------------");
        boolean hasSetter = metaObject.hasSetter("createTime");
        if (hasSetter) {
            this.setUpdateFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update-----------------------");
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (updateTime == null) {
            this.setInsertFieldValByName( "updateTime", LocalDateTime.now(),metaObject);
        }
    }
}
