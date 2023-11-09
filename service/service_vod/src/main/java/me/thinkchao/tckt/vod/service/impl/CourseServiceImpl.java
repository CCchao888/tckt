package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.Course;
import me.thinkchao.tckt.model.vod.CourseDescription;
import me.thinkchao.tckt.model.vod.Subject;
import me.thinkchao.tckt.model.vod.Teacher;
import me.thinkchao.tckt.vo.vod.CourseFormVo;
import me.thinkchao.tckt.vo.vod.CoursePublishVo;
import me.thinkchao.tckt.vo.vod.CourseQueryVo;
import me.thinkchao.tckt.vod.mapper.CourseMapper;
import me.thinkchao.tckt.vod.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

    // 点播课程列表
    @Override
    public Map<String, Object> findPageCourse(Page<Course> pageParam, CourseQueryVo courseQueryVo) {

        //获取条件值
        String title = courseQueryVo.getTitle();
        Long subjectId = courseQueryVo.getSubjectId();//二层分类
        Long subjectParentId = courseQueryVo.getSubjectParentId();//一层分类
        Long teacherId = courseQueryVo.getTeacherId();

        //判断条件为空，封装条件
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(title)){
            wrapper.like("title",title);
        }
        if(!StringUtils.isEmpty(subjectId)){
            wrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(subjectParentId)){
            wrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(teacherId)){
            wrapper.eq("teacher_id",teacherId);
        }

        //调用方法实现条件查询分页
        Page<Course> pages = baseMapper.selectPage(pageParam, wrapper);
        Long totalCount = pages.getTotal();
        Long totalPage = pages.getPages();
        List<Course> records = pages.getRecords();
        //获取id对应的名称，最终显示
        records.stream().forEach(item ->{
            this.getNameById(item);
        });
        //封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("totalCount", totalCount);
        map.put("totalPage", totalPage);
        map.put("records", records);
        return map;

    }

    // 添加课程信息
    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        //添加课程基本信息，操作course表
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.insert(course);
        //添加课程描述信息，操作course_description表
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescription.setCourseId(course.getId());
        courseDescriptionService.save(courseDescription);

        return course.getId();
    }

    // 根据课程id获取课程信息
    @Override
    public CourseFormVo getCourseInfoById(Long id) {
        //得到课程基本信息
        Course course = baseMapper.selectById(id);
        if(course == null) return null;
        //课程描述信息
        //CourseDescription courseDescription = courseDescriptionService.getById(id);
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        CourseDescription courseDescription = courseDescriptionService.getOne(wrapper);
        //封装
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course,courseFormVo);
        //封装描述信息
        if(courseDescription!= null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;

    }

    // 修改课程信息
    @Override
    public void updateCourseId(CourseFormVo courseFormVo) {

        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo,course);
        baseMapper.updateById(course);
        //修改课程描述信息
        QueryWrapper<CourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", course.getId());
        CourseDescription courseDescription = courseDescriptionService.getOne(wrapper);
        courseDescription.setDescription(courseFormVo.getDescription());
        //courseDescription.setCourseId(course.getId());
        courseDescriptionService.updateById(courseDescription);
    }

    // 根据课程id查询发布课程
    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCoursePublishVo(id);
    }

    //课程的最终发布
    @Override
    public void publishCourse(Long id) {
        Course course = baseMapper.selectById(id);
        if(course!= null){
            course.setStatus(1);
            course.setPublishTime(new Date());
            baseMapper.updateById(course);
        }
    }

    //删除课程
    @Override
    public void removeCourseId(Long id) {
        //根据课程id删除小节
        videoService.removeByCourseId(id);
        //根据课程id删除章节
        chapterService.removeByCourseId(id);
        //根据课程id删除课程描述
        courseDescriptionService.removeByCourseId(id);
        //根据课程id删除课程
        baseMapper.deleteById(id);
    }

    // 根据id获取名称
    private Course getNameById(Course course) {
        //根据讲师id获取讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null){
            String name = teacher.getName();
            course.getParam().put("teacherName",name);
        }
        //根据课程分类id获取课程分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null){
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo!= null){
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
        return course;
    }
}
