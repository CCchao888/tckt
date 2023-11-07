package me.thinkchao.tckt.vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.vod.Course;
import me.thinkchao.tckt.vo.vod.CourseFormVo;
import me.thinkchao.tckt.vo.vod.CoursePublishVo;
import me.thinkchao.tckt.vo.vod.CourseQueryVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
public interface CourseService extends IService<Course> {

    // 分页查询点播课程列表
    Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo);

    // 添加课程信息
    Long saveCourseInfo(CourseFormVo courseFormVo);

    // 根据课程id查询课程信息
    CourseFormVo getCourseInfoById(Long id);

    // 修改课程
    void updateCourseId(CourseFormVo courseFormVo);

    // 根据课程id查询课程发布信息
    CoursePublishVo getCoursePublishVo(Long id);

    // 课程的最终发布
    void publishCourse(Long id);
}
