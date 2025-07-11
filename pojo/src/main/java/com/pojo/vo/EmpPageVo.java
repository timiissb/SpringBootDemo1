package com.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmpPageVo {
    private Long id;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phone;
}
