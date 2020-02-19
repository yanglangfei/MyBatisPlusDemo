package com.yanglf.mp;

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
class DeleteTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void deleteById() {
        int rows = userMapper.deleteById(1226417965475422210L);
        System.out.println("影响行数:"+rows);
    }


    @Test
    void deleteAll() {
        int rows = userMapper.deleteAll();
        System.out.println("影响行数:"+rows);
    }


    @Test
    void deleteWithFill() {
        User uer=new User();
        uer.setId(1L);
        uer.setAge(22);
        int rows = userMapper.deleteByIdWithFill(uer);
        System.out.println("影响行数:"+rows);
    }

}
