package me.thinkchao.tckt.vod.controller;


import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.VideoVisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-09
 */
@RestController
@RequestMapping("admin/vod/videoVisitor")
@CrossOrigin
public class VideoVisitorController {
    @Autowired
    private VideoVisitorService videoVisitorService;

    //课程统计的接口
    @GetMapping("findCount/{courseId}/{startDate}/{endDate}")
    public Result findCount(@PathVariable("courseId") Long courseId,
                            @PathVariable("startDate") String startDate,
                            @PathVariable("endDate") String endDate) {
        Map<String, Object> map = videoVisitorService.findCount(courseId, startDate, endDate);
        return Result.success(map);
    }
}

