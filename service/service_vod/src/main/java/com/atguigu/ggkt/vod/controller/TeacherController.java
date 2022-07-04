package com.atguigu.ggkt.vod.controller;


import com.atguigu.ggkt.exception.GgktException;
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
        // 模拟异常

        try {
            int i = 10 / 0;
        } catch (Exception e) {
            throw new GgktException(201, "执行了自定义异常处理GgktException");
        }
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
    /*//条件查询分页列表
    @ApiOperation(value = "获取分页列表")
    @PostMapping("{current}/{limit}")
    public Result findPage(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
        //创建page对象，传递当前页和每页记录数
        Page<Teacher> pageParam = new Page<>(current, limit);
        //获取条件值
        String name = teacherQueryVo.getName();//讲师名称
        Integer level = teacherQueryVo.getLevel();//讲师级别
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();//开始时间
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();//结束时间
        //封装条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(joinDateBegin)) {
            wrapper.ge("join_date",joinDateBegin);
        }
        if(!StringUtils.isEmpty(joinDateEnd)) {
            wrapper.le("join_date",joinDateEnd);
        }
        //调用方法得到分页查询结果
        IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
        return Result.ok(pageModel);
    }*/

    // TODO "total": 0,  pages": 0
    // 3、分页条件查询讲师
    @ApiOperation("分页条件查询讲师")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable Long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQueryVo", value = "查询对象", required = false)
            @RequestBody(required = false) TeacherQueryVo teacherQueryVo) {
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

    // 4、添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // 5、修改-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    // 6、修改-最终实现
    // json{...} 以对象形式传送
    @ApiOperation("修改最终实现")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    // 7、批量删除讲师
    // json数组[1,2,3]  以数组形式传递
    @ApiOperation("批量删除讲师")
    @PostMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }
}

