package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.model.vod.Teacher;
import com.atguigu.ggkt.result.Result;
import com.atguigu.ggkt.vo.vod.TeacherQueryVo;
import com.atguigu.ggkt.vod.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
    /*public List<Teacher> findAllTeacher() {
        // 调用service方法
        List<Teacher> list = teacherService.list();
        return list;
    }*/
    public Result findAllTeacher() {
        // 调用service方法
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    // 2、逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", readOnly = true)
                                @PathVariable Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // TODO "total": 0,  pages": 0
    // 3、分页条件查询讲师
    @ApiOperation("分页条件查询讲师")
    @GetMapping("findQueryPage/{current}/{limit}")
    public Result findPage(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryVo", value = "查询对象", required = false)
                    TeacherQueryVo teacherQueryVo) {
        // 创建page对象
        Page<Teacher> pageParam = new Page<>(current, limit);
        // 判断 TeacherQueryVo 对象是否为空
        if (teacherQueryVo == null) {
            // 查询全部
            IPage<Teacher> pagModel = teacherService.page(pageParam, null);
            return Result.ok(pagModel);
        } else {
            // 获取条件值
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            // 进行非空判断，条件封装
            QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
            if (!StringUtils.isEmpty(name)) {
                wrapper.like("name", name);
            }
            if (!StringUtils.isEmpty(level)) {
                wrapper.like("level", level);
            }
            if (!StringUtils.isEmpty(joinDateBegin)) {// 大于等于入驻时间
                wrapper.ge("join_date", joinDateBegin);
            }
            if (!StringUtils.isEmpty(joinDateEnd)) {// 小于等于结束时间
                wrapper.le("join_date", joinDateEnd);
            }
            // 调用方法分页查询
            IPage<Teacher> pagModel = teacherService.page(pageParam, wrapper);
            // 返回
            return Result.ok(pagModel);
        }
    }
}

