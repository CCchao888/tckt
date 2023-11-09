package me.thinkchao.tckt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.vod.Video;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
public interface VideoService extends IService<Video> {

    // 根据课程id删除所有课程小节
    void removeByCourseId(Long id);

    // 删除小节同时删除视频
    void removeVideoById(Long id);
}
