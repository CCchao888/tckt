package me.thinkchao.tckt.vod.controller;


import me.thinkchao.tckt.model.vod.Teacher;
import me.thinkchao.tckt.vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author thinkchao
 * @since 2023-10-30
 */
@RestController
@RequestMapping("/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // 查询所有讲师 http://localhost:8301/vod/teacher/findAll
    @GetMapping("findAll")
    public List<Teacher> findAllTeacher(){
        //调用service方法
        List<Teacher> list = teacherService.list();
        return list;
    }
    //逻辑删除讲师
    @DeleteMapping("remove/{id}")
    public boolean removeTeacher(@PathVariable Long id){
        boolean isRemoved = teacherService.removeById(id);
        return isRemoved;
    }

}

