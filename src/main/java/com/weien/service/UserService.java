package com.weien.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.User;
import com.pojo.dto.UserPageDTO;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean update(User user);
    boolean add(User user);
    boolean delete(Long id);
    boolean deleteBatch(List<Long> ids);
    IPage<User> findByPage(UserPageDTO userPageDTO);
}
