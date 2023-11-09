package me.thinkchao.tckt.vod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Author:chao
 * Date:2023-11-09
 * Description:
 */
@RestController
@Api(tags = "腾讯云点播")
@RequestMapping("/admin/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    //上传视频的接口
    @ApiOperation("上传视频")
    @PostMapping("upload")
    public Result upload(){
        String fileId = vodService.uploadVideo();
        return Result.success(fileId);
    }

    //删除视频的接口
    @ApiOperation("删除视频")
    @DeleteMapping("remove/{fileId}")
    public Result remove(@PathVariable String fileId){
        vodService.removeVideo(fileId);
        return Result.success(null);
    }

}

















