package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 所属科室
 */
@ApiModel(description = "所属科室入参")
@Data
public class DepartmentReqDto {

    @Schema
    private String name;
}
