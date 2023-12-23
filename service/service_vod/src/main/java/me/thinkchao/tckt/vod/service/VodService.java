package me.thinkchao.tckt.vod.service;

/**
 * Author:chao
 * Date:2023-11-09
 * Description:
 */
public interface VodService {

    // 上传视频
    String uploadVideo();

    // 删除视频
    void removeVideo(String fileId);

    //点播视频的播放接口
    Object getPlayAuth(Long courseId, Long videoId);
}
