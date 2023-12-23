package me.thinkchao.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:chao
 * Date:2023-11-06
 * Description:
 */
public class TestWrite {
    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\test.xlsx";
        //调用方法
        EasyExcel.write(fileName, User.class).sheet("写操作").doWrite(data());
    }
    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("张三" + i);
            list.add(user);
        }
        return list;
    }
}
