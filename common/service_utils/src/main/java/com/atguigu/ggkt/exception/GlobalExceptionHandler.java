package com.atguigu.ggkt.exception;

import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yyh
 * @Date: 2022/07/04 15:06
 * 统一异常处理类
 */

@ControllerAdvice // 使用aop思想
public class GlobalExceptionHandler {

    // 全局异常移除
    @ExceptionHandler(Exception.class)  // 加上此注解才能执行该方法
    @ResponseBody  // 使此方法能返回json数据
    public Result error(Exception e) {
        System.out.println("全局.........");
        e.printStackTrace();
        return Result.fail(null).message("执行全局异常处理！");
    }

    // ArithmeticException (特定异常处理）
    @ExceptionHandler(ArithmeticException.class)  // 加上此注解才能执行该方法
    @ResponseBody  // 使此方法能返回json数据
    public Result error(ArithmeticException e) {
        System.out.println("特定.........");
        e.printStackTrace();
        return Result.fail(null).message("执行ArithmeticException异常处理！");
    }

    // GgktException (自定义异常处理）
    @ExceptionHandler(GgktException.class)  // 加上此注解才能执行该方法
    @ResponseBody  // 使此方法能返回json数据
    public Result error(GgktException e) {
        System.out.println("自定义.........");
        e.printStackTrace();
        return Result.fail(null).code(e.getCode()).message(e.getMsg());
    }

}
