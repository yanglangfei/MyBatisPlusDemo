package com.yanglf.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.yanglf.mp.mapper.UserMapper;
import com.yanglf.mp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@SpringBootTest
@RunWith(value = SpringRunner.class)
@Slf4j
class UpdateTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void update() {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("name", "雷震子").set("email", "lzz@gmai.com");
        int rows = userMapper.update(null, wrapper);
        System.out.println("影响行数:" + rows);
    }


    @Test
    void updateLambda() {
        LambdaUpdateWrapper<User> wrapper = Wrappers.lambdaUpdate();
        wrapper.eq(User::getName, "雷震子").set(User::getAge, 25);
        int rows = userMapper.update(null, wrapper);
        System.out.println("影响行数:" + rows);
    }


    @Test
    void updateLambdaChain() {
        boolean rows = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getName, "雷震子")
                .set(User::getAge, 22).update();
        System.out.println("影响行数:" + rows);
    }


    @Test
    void updateEntity() {
        int version = 4;

        User user = new User();
        user.setAge(24);
        user.setVersion(version);
        user.setId(1226855400017596417L);
        int rows = userMapper.updateById(user);
        System.out.println("影响行数:" + rows);
    }

}
