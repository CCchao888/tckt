package me.thinkchao.tckt.vod.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.vod.Subject;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Api(tags = "课程分类")
@RestController
@RequestMapping("admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //课程分类列表，懒加载，每次查询一层数据
    @ApiOperation("课程分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result getChildSubject(@PathVariable long id){
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.success(list);
    }

    //课程分类导出
    @ApiOperation("课程分类导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        subjectService.exportData(response);
    }

    //课程分类导入
    @ApiOperation("课程分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        subjectService.importData(file);
        return Result.success(null);
    }

}



















