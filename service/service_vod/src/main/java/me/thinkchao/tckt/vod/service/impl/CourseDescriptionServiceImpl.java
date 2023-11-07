package me.thinkchao.tckt.vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.model.vod.CourseDescription;
import me.thinkchao.tckt.vod.mapper.CourseDescriptionMapper;
import me.thinkchao.tckt.vod.service.CourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

}
