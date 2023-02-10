package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 症状
 */
@Data
@ApiModel(description = "症状入参")
public class SymptomReqDto {

    @Schema(description = "name")
    private String name;
}
