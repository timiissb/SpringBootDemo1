package com.weien.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pojo.PageData;
import com.pojo.Result;
import com.pojo.User;
import com.pojo.dto.UserPageDTO;
import com.weien.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;


    @PutMapping("/update")
    public Result<String> update(@RequestBody User user) {
       if (userService.update(user)){
           return Result.success();
       }
        return Result.success();
    }

    @PostMapping("/adduser")
    public Result<String> add(@RequestBody User user){
        if(userService.add(user)){
            return Result.success();
        }else{
            return Result.error("添加失败");
        }
    }

    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        if(userService.delete(id)){
            return Result.success();
        }else{
            return Result.error("删除失败");
        }
    }

    @PostMapping("/delete")
    public Result<String> deleteBatch(@RequestBody List<Long> ids) {
        if (userService.deleteBatch(ids)){
            return Result.success();
        }else{
            return Result.error("删除失败");
        }
    }

    @GetMapping("/page")
    public Result<PageData<User>> findPage(UserPageDTO userPageDTO) {
        // 调用一次 userService.findByPage 避免重复查询
        IPage<User> pageResult = userService.findByPage(userPageDTO);

        List<User> list = pageResult.getRecords();
        long total = pageResult.getTotal();

        PageData<User> page = new PageData<>(list, total);
        return Result.success(page);
    }

}
