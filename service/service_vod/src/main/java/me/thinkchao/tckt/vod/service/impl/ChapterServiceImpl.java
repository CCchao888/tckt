package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.Chapter;
import me.thinkchao.tckt.model.vod.Video;
import me.thinkchao.tckt.vo.vod.ChapterVo;
import me.thinkchao.tckt.vo.vod.VideoVo;
import me.thinkchao.tckt.vod.mapper.ChapterMapper;
import me.thinkchao.tckt.vod.service.ChapterService;
import me.thinkchao.tckt.vod.service.VideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    // 大纲列表---课程章节树
    @Override
    public List<ChapterVo> getTreeList(Long courseId) {
        //定义最终数据list集合
        List<ChapterVo> finalChapterList = new ArrayList<>();

        //根据courseId获取所有章节
        QueryWrapper<Chapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<Chapter> chapterList = baseMapper.selectList(wrapperChapter);

        //根据courseId获取所有章节里面的小节
        LambdaQueryWrapper<Video> wrapperVideo = new LambdaQueryWrapper<>();
        wrapperVideo.eq(Video::getCourseId, courseId);
        List<Video> videoList = videoService.list(wrapperVideo);

        //封装章节
        for(Chapter chapter : chapterList){
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            finalChapterList.add(chapterVo);

            //封装小节
            List<VideoVo> videoVoList = new ArrayList<>();
            for(Video video : videoList){
                //判断小节是在哪个章节下
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);

        }
        return finalChapterList;
    }
}
