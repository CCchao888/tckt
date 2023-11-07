package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.Video;
import me.thinkchao.tckt.vod.mapper.VideoMapper;
import me.thinkchao.tckt.vod.service.VideoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
