package me.thinkchao.tckt.vod.controller;


import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.vod.Video;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@CrossOrigin
@RestController
@RequestMapping("admin/vod/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Video video = videoService.getById(id);
        return Result.success(video);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody Video video) {
        videoService.save(video);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody Video video) {
        videoService.updateById(video);
        return Result.success(null);
    }

    @ApiOperation(value = "删除小节")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        videoService.removeVideoById(id);
        return Result.success(null);
    }

}

