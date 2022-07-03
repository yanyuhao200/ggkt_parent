package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.vod.service.TeacherService;
import com.atguigu.ggkt.model.vod.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2022-07-02
 */
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // 1、查询所有讲师
    // http://localhost:8301/admin/vod/teacher/findAll
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher() {
        List<Teacher> list = teacherService.list();
        return list;
    }

    // 2、逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public boolean removeTeacher(@ApiParam(name = "id", value = "ID", readOnly = true)
                                 @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);

        return isSuccess;
    }
}

