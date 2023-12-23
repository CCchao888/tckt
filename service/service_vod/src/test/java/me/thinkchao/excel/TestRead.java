package me.thinkchao.excel;

import com.alibaba.excel.EasyExcel;

/**
 * Author:chao
 * Date:2023-11-06
 * Description:
 */
public class TestRead {

    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\test.xlsx";
        //调用方法进行读取
        EasyExcel.read(fileName, User.class, new ExcelListener()).sheet().doRead();
    }

}
