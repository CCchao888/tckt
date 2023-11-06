package me.thinkchao.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * Author:chao
 * Date:2023-11-06
 * Description:
 */
public class ExcelListener extends AnalysisEventListener<User> {

    // 每解析一行数据都会调用,即一行一行读取数据，把每行数据都封装到User对象中
    // 从excel第二行开始读取
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(user);
    }

    //读取表头内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext analysisContext) {
        System.out.println("表头："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
