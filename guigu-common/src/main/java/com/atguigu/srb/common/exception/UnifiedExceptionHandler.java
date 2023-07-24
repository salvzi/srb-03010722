package com.atguigu.srb.common.exception;


import com.atguigu.srb.common.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Component
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public R runtimeExceptionHandler(RuntimeException e){
        System.out.println("运行时通用异常"+e);
        R error = R.error();
        error.setMessage("错误信息："+e.getMessage());
        log.error(e.getMessage());
        return error;
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public R badSqlGrammarExceptionHandler(BadSqlGrammarException e){
        System.out.println("jdbc异常"+e);
        R error = R.error();
        error.setMessage("错误信息："+e.getMessage()+"保存到数据库表中");
        log.error(e.getMessage());
        return error;
    }

    @ExceptionHandler(SrbBusinessException.class)
    public R srbBusinessExceptionHandler(SrbBusinessException e){
        System.out.println("业务异常"+e);
        R error = R.error();
        error.setMessage(e.getMessage());
        log.error(e.getMessage());
        return error;
    }



}
