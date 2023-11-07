package me.thinkchao.tckt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.thinkchao.tckt.exception.TcktException;
import me.thinkchao.tckt.model.vod.Subject;
import me.thinkchao.tckt.vo.vod.SubjectEeVo;
import me.thinkchao.tckt.vod.listener.SubjectListener;
import me.thinkchao.tckt.vod.mapper.SubjectMapper;
import me.thinkchao.tckt.vod.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> selectSubjectList(long id) {
        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        List<Subject> subjectList = baseMapper.selectList(queryWrapper);
        //遍历list集合每个Subject对象，判断是否有下一层数据，有则设置hasChildren=true
        for (Subject subject : subjectList) {
            QueryWrapper<Subject> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("parent_id", subject.getId());
            Integer count = baseMapper.selectCount(queryWrapper1);
            subject.setHasChildren(count > 0);
        }
        return subjectList;
    }

    //课程分类导出数据
    @Override
    public void exportData(HttpServletResponse response) {
        try{
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询课程分类表的所有数据
            List<Subject> subjectList = baseMapper.selectList(null);
            // List<Subject>转变为List<SubjectEevo>
            List<SubjectEeVo> subjectEeVoList = new ArrayList<>(subjectList.size());
            subjectList.forEach(subject -> {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                BeanUtils.copyProperties(subject, subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            });

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类").doWrite(subjectEeVoList);

        }catch (Exception e){
            throw new TcktException(20001, "导出失败");
        }

    }

    //课程分类导入数据
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(), SubjectEeVo.class, subjectListener)
                    .sheet().doRead();
        } catch (IOException e) {
            throw new TcktException(20001, "导入失败");
        }
    }


}
