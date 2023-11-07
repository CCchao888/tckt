package me.thinkchao.tckt.vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.thinkchao.tckt.model.vod.Course;
import me.thinkchao.tckt.vo.vod.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
public interface CourseMapper extends BaseMapper<Course> {

    //根据课程id查询课程发布信息
    CoursePublishVo selectCoursePublishVo(Long id);
}
