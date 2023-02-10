package org.example.execption;

public class CustomException extends Exception {
    private Integer code;
    private String message;

    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
