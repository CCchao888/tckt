package me.thinkchao.tckt.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Author:chao
 * Date:2023-11-05
 * Description:
 */
public interface FileService {
    //文件上传
    String upload(MultipartFile file);

}
