package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 发病部位
 */
@Data
@ApiModel(description = "发病部位入参")
public class PartReqDto {

    @Schema(description = "name")
    private String name;

}
