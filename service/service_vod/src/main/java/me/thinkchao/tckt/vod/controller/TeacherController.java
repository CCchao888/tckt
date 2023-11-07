package me.thinkchao.tckt.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import me.thinkchao.tckt.model.vod.Teacher;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.vod.TeacherQueryVo;
import me.thinkchao.tckt.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-10-30
 */

@Api(tags = "讲师管理接口")
@RestController
@RequestMapping("admin/vod/teacher")
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询所有讲师")
    // 查询所有讲师 http://localhost:8301/vod/teacher/findAll
    @GetMapping("findAll")
    public Result findAllTeacher(){
        //调用service方法
        List<Teacher> list = teacherService.list();
        return Result.success(list);
    }
    //逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id",value = "ID") @PathVariable Long id){
        boolean isRemoved = teacherService.removeById(id);
        if(isRemoved){
            return Result.success(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                            @RequestBody(required = false) TeacherQueryVo teacherQueryVo){
        //创建page对象
        Page<Teacher> pageParam = new Page<>(current,limit);

        //判断TeacherQueryVo对象是否为空
        if(teacherQueryVo == null){
            Page<Teacher> page = teacherService.page(pageParam,null);
            return Result.success(page);
        }else{
            //获取条件值
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
            if(!StringUtils.isEmpty(name)){
                queryWrapper.like("name",name);
            }
            if(!StringUtils.isEmpty(level)){
                queryWrapper.eq("level",level);
            }
            if(!StringUtils.isEmpty(joinDateBegin)){
                queryWrapper.ge("join_date",joinDateBegin);
            }
            if(!StringUtils.isEmpty(level)){
                queryWrapper.le("join_date",joinDateEnd);
            }
            //调用方法分页查询
            Page<Teacher> page = teacherService.page(pageParam, queryWrapper);
            return Result.success(page);
        }
    }

    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.save(teacher);
        if(isSuccess){
            return Result.success(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("根据id查询讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable long id){
        Teacher teacher = teacherService.getById(id);
        return Result.success(teacher);
    }
    @ApiOperation("修改讲师的最终实现")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody Teacher teacher){
        boolean isSuccess = teacherService.updateById(teacher);
        if(isSuccess){
            return Result.success(null);
        }else {
            return Result.fail(null);
        }
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList){
        boolean isSuccess = teacherService.removeByIds(idList);
        if(isSuccess){
            return Result.success(null);
        }else {
            return Result.fail(null);
        }
    }







}















