package com.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Empl {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
