package com.weien.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.PageData;
import com.pojo.Result;
import com.pojo.dto.EmpAddDTO;
import com.pojo.dto.EmpPageDTO;
import com.pojo.vo.EmpPageVo;
import com.weien.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     * @param empPageDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageData<EmpPageVo>> findPage(EmpPageDTO empPageDTO){
        IPage<EmpPageVo> page = empService.findByPage(empPageDTO);
        List<EmpPageVo> list = page.getRecords();
        long total = page.getTotal();
        PageData<EmpPageVo> pageData = new PageData<>(list, total);
        return Result.success(pageData);
    }

    /**
     * 添加员工
     * @param empAddDTO
     * @return
     */
    @PostMapping("/add")
    public Result<String> addEmp(@RequestBody EmpAddDTO empAddDTO){
        empService.addEmp(empAddDTO);
        return Result.success();
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteEmp(@PathVariable Long id){
        empService.deleteEmp(id);
        return Result.success();
    }

}
