package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.Video;
import me.thinkchao.tckt.vod.mapper.VideoMapper;
import me.thinkchao.tckt.vod.service.VideoService;
import me.thinkchao.tckt.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Autowired
    private VodService vodService;

    //根据课程id删除所有小节，用于删除课程
    @Override
    public void removeByCourseId(Long id) {
        //根据课程id查询所有小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        List<Video> videoList = baseMapper.selectList(wrapper);
        //遍历所有小节集合得到每个小节，获取每个小节视频id
        for (Video video : videoList) {
            String videoSourceId = video.getVideoSourceId();
            //判断视频id是否为空，是则删除腾讯云中的视频
            if(!StringUtils.isEmpty(videoSourceId)){
                vodService.removeVideo(videoSourceId);
            }
        }
        //根据课程id删除所有小节
        baseMapper.delete(wrapper);
    }

    //删除小节同时删除视频，用于删除小节
    @Override
    public void removeVideoById(Long id) {
        //id查询小节
        Video video = baseMapper.selectById(id);
        //获取小节里面的视频id
        String videoSourceId = video.getVideoSourceId();
        //判断视频id是否为空，，是则删除腾讯云中的视频
        if(!StringUtils.isEmpty(videoSourceId)){
            vodService.removeVideo(videoSourceId);
        }
        //根据id删除小节
        baseMapper.deleteById(id);
    }
}
