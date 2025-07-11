package com.pojo.dto;

import lombok.Data;

@Data
public class UserPageDTO {
    private String username;
    private Integer pageNum;
    private Integer pageSize;
}
