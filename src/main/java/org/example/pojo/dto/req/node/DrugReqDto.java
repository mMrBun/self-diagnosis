package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 药品
 */
@Data
@ApiModel(description = "药品入参")
public class DrugReqDto {

    @Schema(description = "name")
    private String name;
}
