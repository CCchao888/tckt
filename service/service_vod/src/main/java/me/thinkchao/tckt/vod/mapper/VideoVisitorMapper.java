package me.thinkchao.tckt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.thinkchao.tckt.model.vod.VideoVisitor;
import me.thinkchao.tckt.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-09
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    //课程统计的接口
    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId,
                                        @Param("startDate") String startDate,
                                        @Param("endDate") String endDate);
}
