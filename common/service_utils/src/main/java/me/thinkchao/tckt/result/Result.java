package me.thinkchao.tckt.result;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Author:chao
 * Date:2023-11-01
 * Description: 这是一个统一返回结果类
 */

@ApiModel(value = "全局统一返回结果")
@Data
public class Result <T>{

    private Integer code;   //状态码

    private String message;  //返回状态信息（成功、失败）

    private T data;     //返回数据

    public Result(){}

    //成功的方法
    public static<T> Result<T> success(T data){
        Result<T> result = new Result<>();
        if(data != null)result.setData(data);
        result.setCode(ResultCodeEnum.SUCCESS.getCode());
        result.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return result;
    }

    //失败的方法
    public static<T> Result<T> fail(T data){
        Result<T> result = new Result<>();
        if(data != null)result.setData(data);
        result.setCode(ResultCodeEnum.FAIL.getCode());
        result.setMessage(ResultCodeEnum.FAIL.getMessage());
        return result;
    }


}
