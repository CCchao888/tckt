package me.thinkchao.tckt.vod.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.result.Result;
import me.thinkchao.tckt.vod.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Author:chao
 * Date:2023-11-05
 * Description:
 */
@Api(tags = "文件上传接口")
@RestController
@RequestMapping("/admin/vod/file")
@CrossOrigin
public class FileUpLoadController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("upload")
    public Result uploadFile(MultipartFile file){
        String url = fileService.upload(file);
        return Result.success(url).message("上传文件成功");
    }
}
