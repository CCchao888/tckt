package me.thinkchao.tckt.vod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.exception.TcktException;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.VodService;
import me.thinkchao.tckt.vod.utils.ConstantPropertiesUtil;
import me.thinkchao.tckt.vod.utils.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

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

    //返回客户端上传视频的签名
    @GetMapping("sign")
    public Result sign(){
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(ConstantPropertiesUtil.ACCESS_KEY_ID);
        sign.setSecretKey(ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.success(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            throw new TcktException(20001, "获取签名失败");
        }
    }

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

















