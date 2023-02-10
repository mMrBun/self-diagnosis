package org.example.pojo.dto.req.relation;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Data
@ApiModel(description = "药品关系入参")
public class HAS_DRUG_ReqDto {

    @Schema(description = "开始节点")
    private Long parentId;

    @Schema(description = "结束节点")
    private Long childId;

    @Schema(description = "节点关系名称")
    private String name;
}
