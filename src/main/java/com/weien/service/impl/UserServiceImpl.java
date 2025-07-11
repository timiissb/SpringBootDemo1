package com.weien.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.dto.UserPageDTO;
import com.weien.mapper.UserMapper;
import com.pojo.User;
import com.weien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.selectList(null);
    }

    @Override
    public boolean update(User user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getId, user.getId())
                .set(User::getUsername, user.getUsername())
                .set(User::getPassword, user.getPassword());
        return userMapper.update(null, wrapper) > 0;
    }

    @Override
    public boolean add(User user) {
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean delete(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean deleteBatch(List<Long> ids) {
        return userMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public IPage<User> findByPage(UserPageDTO userPageDTO) {
        Page<User> page = new Page<>(userPageDTO.getPageNum(), userPageDTO.getPageSize());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (userPageDTO.getUsername() != null && !userPageDTO.getUsername().isEmpty()) {
            wrapper.like(User::getUsername, userPageDTO.getUsername());
        }
        return userMapper.selectPage(page, wrapper);
    }
}
