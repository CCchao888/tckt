package me.thinkchao.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * Author:chao
 * Date:2023-11-06
 * Description:
 */
@Data
public class User {

    @ExcelProperty(value = "用户编号",index = 0)
    private Integer id;

    @ExcelProperty(value = "用户姓名",index = 1)
    private String name;


}
