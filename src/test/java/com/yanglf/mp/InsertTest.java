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
import java.util.List;

@SpringBootTest
@RunWith(value = SpringRunner.class)
@Slf4j
class InsertTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void insert() {
        User  user=new User();
        user.setName("李雯");
        user.setAge(23);
        user.setEmail("lw@douyin.com");
        user.setManagerId(1226417488859869186L);
        int rows = userMapper.insert(user);
        System.out.println("影响行数:"+rows+" user"+user);
    }

}
