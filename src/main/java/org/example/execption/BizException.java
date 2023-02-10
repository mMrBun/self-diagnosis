package org.example.execption;

/**
 * @author zouzhangli
 * @date 2022/8/1  13:55
 * @description
 */
public class BizException extends RuntimeException {

    private final int code;

    private final String message;

    public BizException(ResponseCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public BizException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
