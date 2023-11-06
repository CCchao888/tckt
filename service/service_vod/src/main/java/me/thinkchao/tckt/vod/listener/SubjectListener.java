package me.thinkchao.tckt.vod.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import me.thinkchao.tckt.model.vod.Subject;
import me.thinkchao.tckt.vo.vod.SubjectEeVo;
import me.thinkchao.tckt.vod.mapper.SubjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Author:chao
 * Date:2023-11-06
 * Description:
 */
@Component
public class SubjectListener extends AnalysisEventListener<SubjectEeVo> {

    //注入mapper
    @Autowired
    private SubjectMapper subjectMapper;

    //一行一行，读取数据，从第二行开始
    @Override
    public void invoke(SubjectEeVo subjectEeVo, AnalysisContext analysisContext) {
        Subject subject = new Subject();
        //subject 转为 subjectEevo
        BeanUtils.copyProperties(subjectEeVo, subject);
        subjectMapper.insert(subject);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
