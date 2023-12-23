package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.VideoVisitor;
import me.thinkchao.tckt.vo.vod.VideoVisitorCountVo;
import me.thinkchao.tckt.vod.mapper.VideoVisitorMapper;
import me.thinkchao.tckt.vod.service.VideoVisitorService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-09
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    // 统计视频小节来访者数量
    @Override
    public Map<String, Object> findCount(Long courseId, String startDate, String endDate) {
        //调用mapper中的方法
        List<VideoVisitorCountVo> videoVisitorCountVoList = baseMapper.findCount(courseId, startDate, endDate);

        //创建map集合
        Map<String, Object> map = new HashMap<>();
        //创建两个list集合，应该代表所有日期，一个代表日期对应的数量
        //封装数据
        List<String> dateList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getJoinTime).collect(Collectors.toList());
        List<Integer> countList = videoVisitorCountVoList.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());

        map.put("xData",dateList);
        map.put("yData",countList);

        return map;
    }
}
