package me.thinkchao.tckt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-09
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    // 统计视频小节来访者数量
    Map<String, Object> findCount(Long courseId, String startDate, String endDate);
}
