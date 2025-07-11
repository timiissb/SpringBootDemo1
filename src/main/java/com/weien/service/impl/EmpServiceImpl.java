package com.weien.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pojo.PageData;
import com.pojo.dto.EmpAddDTO;
import com.pojo.dto.EmpPageDTO;
import com.pojo.entity.Empl;
import com.pojo.vo.EmpPageVo;
import com.weien.mapper.EmpMapper;
import com.weien.service.EmpService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public IPage<EmpPageVo> findByPage(EmpPageDTO empPageDTO) {
        Page<Empl> page = new Page<>(empPageDTO.getPageNum(), empPageDTO.getPageSize());
        LambdaQueryWrapper<Empl> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(empPageDTO.getUsername())) {
            wrapper.like(Empl::getUsername, empPageDTO.getUsername());
        }
        IPage<Empl> emplIpage = empMapper.selectPage(page,wrapper);
        List<EmpPageVo> list = emplIpage.getRecords().stream()
                .map(this::toVo)
                .toList();
        Page<EmpPageVo> voPage = new Page<>(emplIpage.getCurrent(), emplIpage.getSize(), emplIpage.getTotal());
        voPage.setRecords(list);

       return voPage;
    }

    @Override
    public void addEmp(EmpAddDTO empAddDTO) {
        Empl empl = new Empl();
        BeanUtils.copyProperties(empAddDTO,empl);
        empl.setCreateTime(LocalDateTime.now());
        empl.setUpdateTime(LocalDateTime.now());
        empMapper.insert(empl);
    }


    private EmpPageVo toVo(Empl empl) {
        EmpPageVo vo = new EmpPageVo();
        BeanUtils.copyProperties(empl, vo); // 来自 Spring 的 org.springframework.beans.BeanUtils
        return vo;
    }
}
