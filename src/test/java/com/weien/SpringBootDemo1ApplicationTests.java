package com.weien;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.weien.mapper.UserMapper;
import com.pojo.User;
import com.weien.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootDemo1ApplicationTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @Test
    void selectById() {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .select(User::getUsername, User::getPassword)
                .eq(User::getUsername, "admin");
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }

    @Test
    void insert() {
        User user = new User();
        user.setUsername("admin001");
        user.setPassword("123456");
        userMapper.insert(user);
    }

    @Test
    void save(){
        System.out.println(userMapper.selectCount(null));
    }

}
