package org.example.execption;

import org.example.pojo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zouzhangli
 * @date 2022/7/14  14:28
 * @description 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求参数不全
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ApiResponse missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("请求参数不全:【"+e.getMessage()+"】");
        return ApiResponse.fail(400,e.getMessage());
    }

    /**
     * 请求参数不全
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("请求参数不全:【"+e.getMessage()+"】");
        return ApiResponse.fail(400,e.getMessage());
    }

    @ExceptionHandler(BizException.class)
    public ApiResponse handleBizException(BizException e) {
        return ApiResponse.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ApiResponse.fail(500,"请查看日志获取详细错误信息");
    }

    @ExceptionHandler(CustomException.class)
    public ApiResponse customException(Exception e) {
        log.error(e.getMessage(), e);
        return ApiResponse.fail(ResponseCode.ERROR_19.getCode(), ResponseCode.ERROR_19.getMessage());
    }

}
