package me.thinkchao.tckt.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import me.thinkchao.tckt.model.vod.Chapter;
import me.thinkchao.tckt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author thinkchao
 * @since 2023-11-06
 */
public interface ChapterService extends IService<Chapter> {

    // 获取课程章节树形结构
    List<ChapterVo> getTreeList(Long courseId);
}
