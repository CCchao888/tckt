package me.thinkchao.tckt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */

public interface SubjectService extends IService<Subject> {
    /**
     * 根据给定的id，查询指定Subject列表
     * @param id 主键id
     * @return 指定Subject列表
     */
    List<Subject> selectSubjectList(long id);

    /**
     * 导出数据
     * @param response 响应
     */
    void exportData(HttpServletResponse response);

    /**
     * 导入数据
     * @param file 导入文件
     */
    void importData(MultipartFile file);
}
