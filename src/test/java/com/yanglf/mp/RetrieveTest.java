package com.yanglf.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.yanglf.mp.config.MyBatisPlusConfig;
import com.yanglf.mp.mapper.UserMapper;
import com.yanglf.mp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(value = SpringRunner.class)
@Slf4j
class RetrieveTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void selectById() {
        User user = userMapper.selectById(1226417965475422210L);
        System.out.println(user);
    }


    @Test
    void selectList() {
        List<Map<String, Object>> userList = userMapper.selectMaps(null);
        userList.forEach(System.out::println);
    }


    @Test
    void selectByIds() {
        List<Long> ids = Arrays.asList(1226417965475422210L, 1226417488859869186L);
        List<User> userList = userMapper.selectBatchIds(ids);
        userList.forEach(System.out::println);
    }


    @Test
    void selectByMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "张柏芝");
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }


    @Test
    void selectByWrapper() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.like("name", "爱").lt("age", 30);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectByWrapper2() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.like("name", "爱")
                .between("age", 25, 30)
                .isNotNull("email");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 姓张 或者 年龄大于等于25 按年龄倒叙 年龄相同 按id正序排序
     */
    @Test
    void selectByWrapper3() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.likeRight("name", "张")
                .or().ge("age", 25)
                .orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 创建日期 2020-2-9 并且直属上级姓张
     */
    @Test
    void selectByWrapper4() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2020-02-09")
                .inSql("manager_id", "select id from user where name like '张%'");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 姓王  并且 （年龄小于40 或者  email 不为空）
     */
    @Test
    void selectByWrapper5() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.likeRight("name", "王")
                .and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 姓王 或者 (年龄小于 40 并且 邮箱不为空)
     */
    @Test
    void selectByWrapper6() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.likeRight("name", "王")
                .or(wq -> wq.lt("age", 40).isNotNull("email"));
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * （年龄小于40 或者 邮箱不为空） 并且 姓赵
     */
    @Test
    void selectByWrapper7() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.nested(wq -> wq.lt("age", 40).or().isNotNull("email"))
                .likeRight("name", "赵");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 年龄 为 21,22,25
     */
    @Test
    void selectByWrapper8() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.in("age", 21, 22, 25);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 只返回满足条件的一条数据
     */
    @Test
    void selectByWrapper9() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.in("age", 21, 22, 25)
                .last(" limit 1");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 只查询姓名  和  email 字段
     */
    @Test
    void selectByWrapperSuper1() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.in("age", 21, 22, 25).select("name", "email");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    /**
     * 不查询  create_time  和  manager_id 字段
     */
    void selectByWrapperSuper2() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.in("age", 21, 22, 25)
                .select(User.class, info -> !info.getColumn().equals("create_time")
                        && !info.getColumn().equals("manager_id"));
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 根据传入参数动态查询
     */
    @Test
    void testCondition() {
        String name = "";
        String email = "x";
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.likeRight(StringUtils.isNotEmpty(name), "name", name)
                .isNotNull(StringUtils.isNotEmpty(email), "email");
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectByWrapperEntity() {
        User user = new User();
        user.setName("赵丽颖");
        QueryWrapper<User> wrapper = Wrappers.query(user);
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 安装直属上级分组 查询每组的平均年龄，最大年龄 最小年龄 并且只取 年龄总和小于 500 的 组
     */
    @Test
    void selectMap() {
        QueryWrapper<User> wrapper = Wrappers.query();
        wrapper.select("avg(age) avg_age", "min(age) min_age", "max(age) max_age")
                .groupBy("manager_id")
                .having("sum(age) < {0}", 500);
        List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectLambda() {
        LambdaQueryWrapper<User> wrapper=Wrappers.lambdaQuery();
        wrapper.likeRight(User::getName,"张")
                .lt(User::getAge,40);
        List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectLambda2() {
        LambdaQueryWrapper<User> wrapper=Wrappers.lambdaQuery();
        wrapper.likeRight(User::getName,"张")
                .and( wq -> wq.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<Map<String, Object>> userList = userMapper.selectMaps(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectLambda3() {
        List<User> userList = new LambdaQueryChainWrapper<>(userMapper).likeRight(User::getName, "张").list();
        userList.forEach(System.out::println);
    }


    @Test
    void selectAll() {
        LambdaQueryWrapper<User> wrapper=Wrappers.lambdaQuery();
        List<User> userList = userMapper.selectAll(wrapper);
        userList.forEach(System.out::println);
    }


    @Test
    void selectDynamicList() {
        MyBatisPlusConfig.dynamicTableName.set("user_2020");

        LambdaQueryWrapper<User> wrapper=Wrappers.lambdaQuery();
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(System.out::println);
    }

}
