package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 并发症
 */
@ApiModel(description = "并发症入参")
@Data
public class ComplicationReqDto {

    @Schema(description = "名称")
    private String name;
}
