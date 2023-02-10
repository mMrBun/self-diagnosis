package org.example.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ApiModel(value = "响应体",description = "统一响应类")
public class ApiResponse<T> implements Serializable {

    @Schema(description = "返回码")
    private int code;

    @Schema(description = "返回信息说明")
    private String msg;

    @Schema(description = "返回数据")
    private T data;

    /**
     * 成功返回
     */
    public static <T> ApiResponse<T> success(T object) {
        return new ApiResponse<T>(200, "成功", object);
    }

    /**
     * 失败返回
     */
    public static ApiResponse<Object> fail(int code, String msg) {
        return new ApiResponse<>(code, msg, "");
    }

}
