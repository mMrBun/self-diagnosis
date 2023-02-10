package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 别名入参
 */
@Data
@ApiModel(description = "别名入参")
public class AliasReqDto {

    @Schema(description = "别名")
    private String name;

}
