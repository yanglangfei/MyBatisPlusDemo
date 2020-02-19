package com.yanglf.mp.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.yanglf.mp.method.DeleteAllMethod;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yanglf
 * @sine 2020.02.10
 * @descriptipon
 * @see
 */
@Component
public class MySqlInjector extends DefaultSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        methodList.add(new DeleteAllMethod());
        // 选装件 批量插入时 排除  age  和  deleted 字段
        methodList.add(new InsertBatchSomeColumn(t -> !t.isLogicDelete() && !"age".equals(t.getColumn())));
        // 逻辑删除时  填充某个字段
        methodList.add(new LogicDeleteByIdWithFill());
        // 更新时  不更新 name 字段
        methodList.add(new AlwaysUpdateSomeColumnById(t -> !"name".equals(t.getColumn())));
        return methodList;
    }
}
