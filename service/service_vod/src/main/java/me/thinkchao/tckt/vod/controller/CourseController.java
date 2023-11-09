package me.thinkchao.tckt.vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.vod.Course;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.vod.CourseFormVo;
import me.thinkchao.tckt.vo.vod.CoursePublishVo;
import me.thinkchao.tckt.vo.vod.CourseQueryVo;
import me.thinkchao.tckt.vod.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@RestController
@CrossOrigin
@RequestMapping("admin/vod/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    //添加课程基本信息
    @ApiOperation("添加课程基本信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId =  courseService.saveCourseInfo(courseFormVo);
        return Result.success(courseId);
    }

    //根据id获取课程信息
    @ApiOperation("根据id获取课程信息")
    @GetMapping("get/{id}")
    public Result getCourseById(@PathVariable Long id) {
        CourseFormVo courseFormVo = courseService.getCourseInfoById(id);
        return Result.success(courseFormVo);
    }
    //修改课程信息
    @ApiOperation("修改课程信息")
    @PostMapping("update")
    public Result updateCourse(@RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseId(courseFormVo);
        return Result.success(courseFormVo.getId());
    }

    //点播课程列表
    @ApiOperation("点播课程列表")
    @GetMapping("{page}/{limit}")
    public Result courseList(@PathVariable Long page,
                             @PathVariable Long limit,
                             CourseQueryVo courseQueryVo) {
        Page<Course> pageParam = new Page<>(page, limit);
        Map<String,Object> map = courseService.findPageCourse(pageParam, courseQueryVo);

        return Result.success(map);
    }

    //根据课程id查询发布课程信息
    @ApiOperation("根据课程id查询发布课程信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVo(@PathVariable Long id) {
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.success(coursePublishVo);
    }

    //课程的最终发布
    @ApiOperation("课程的最终发布")
    @PutMapping("publishCourse/{id}")
    public Result publishCourse(@PathVariable Long id) {
        courseService.publishCourse(id);
        return Result.success(null);
    }

    //删除课程
    @ApiOperation("删除课程")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        courseService.removeCourseId(id);
        return Result.success(null);
    }
}

