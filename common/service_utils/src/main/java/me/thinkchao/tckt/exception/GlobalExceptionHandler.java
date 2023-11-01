package me.thinkchao.tckt.exception;

import me.thinkchao.tckt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:chao
 * Date:2023-11-01
 * Description:
 */
@ControllerAdvice  //aop
public class GlobalExceptionHandler {

    //全局异常处理
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null).message("执行全局异常处理");
    }

    //特定异常处理
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.fail(null).message("执行特定异常处理");
    }

    //自定义异常处理
    @ResponseBody
    @ExceptionHandler(TcktException.class)
    public Result error(TcktException e){
        e.printStackTrace();
        return Result.fail(null).message(e.getMessage()).code(e.getCode());
    }

}
