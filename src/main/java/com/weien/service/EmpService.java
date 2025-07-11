package com.weien.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.PageData;
import com.pojo.dto.EmpAddDTO;
import com.pojo.dto.EmpPageDTO;
import com.pojo.vo.EmpPageVo;

public interface EmpService {
    IPage<EmpPageVo> findByPage(EmpPageDTO empPageDTO);
    void addEmp(EmpAddDTO empAddDTO);
}
