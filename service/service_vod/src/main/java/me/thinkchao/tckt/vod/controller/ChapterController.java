package me.thinkchao.tckt.vod.controller;


import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.vod.Chapter;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vo.vod.ChapterVo;
import me.thinkchao.tckt.vod.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@RestController
@RequestMapping("admin/vod/chapter")
//@CrossOrigin
public class ChapterController {

    @Autowired
    private ChapterService chapterService;

    //大纲列表（章节和小节）
    @ApiOperation("大纲列表（章节和小节）")
    @GetMapping("getNestedTreeList/{courseId}")
    public Result getTreeList(@PathVariable Long courseId){
        List<ChapterVo> list = chapterService.getTreeList(courseId);
        return Result.success(list);
    }

    //添加章节
    @PostMapping("save")
    public Result save(@RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.success(null);
    }

    //修改-根据id查询
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id){
        Chapter chapter = chapterService.getById(id);
        return Result.success(chapter);
    }

    //修改-最终实现
    @PostMapping("update")
    public Result update(@RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.success(null);
    }

    //删除章节
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Long id){
        chapterService.removeById(id);
        return Result.success(null);
    }
}

