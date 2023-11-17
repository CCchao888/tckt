package me.thinkchao.tckt.client.course;

import io.swagger.annotations.ApiOperation;
import me.thinkchao.tckt.model.vod.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Author:chao
 * Date:2023-11-12
 * Description:
 */
@FeignClient(value = "service-vod")
public interface CourseFeignClient {

    @ApiOperation("根据ID查询课程")
    @GetMapping("/api/vod/course/inner/getById/{courseId}")
    Course getById(@PathVariable Long courseId);

    @ApiOperation("根据关键字查询课程")
    @GetMapping("/api/vod/course/inner/findByKeyword/{keyword}")
    List<Course> findByKeyword(@PathVariable String keyword);
}
