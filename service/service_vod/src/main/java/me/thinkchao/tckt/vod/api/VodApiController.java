package me.thinkchao.tckt.vod.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:chao
 * Date:2023-11-15
 * Description:
 */
@Api(tags = "腾讯视频点播")
@RestController
@RequestMapping("/api/vod")
public class VodApiController {

    @Autowired
    private VodService vodService;

    //点播视频的播放接口
    @GetMapping("getPlayAuth/{courseId}/{videoId}")
    public Result getPlayAuth(
            @ApiParam(value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(value = "视频id", required = true)
            @PathVariable Long videoId) {
        return  Result.success(vodService.getPlayAuth(courseId, videoId));
    }
}
