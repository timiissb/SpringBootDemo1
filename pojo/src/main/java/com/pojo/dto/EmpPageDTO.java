package com.pojo.dto;

import lombok.Data;

@Data
public class EmpPageDTO {
    private String username;
    private Integer pageNum;
    private Integer pageSize;
}
