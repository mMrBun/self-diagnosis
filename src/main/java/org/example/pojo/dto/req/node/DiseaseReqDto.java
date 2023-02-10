package org.example.pojo.dto.req.node;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病
 */
@Data
@ApiModel(description = "疾病入参")
public class DiseaseReqDto {

    @Schema(description = "发病人群")
    private String age;

    @Schema(description = "检查项目")
    private String checklist;

    @Schema(description = "是否传染")
    private String infection;

    @Schema(description = "是否医保")
    private String insurance;

    @Schema(description = "费用")
    private String money;

    @Schema(description = "疾病名称")
    private String name;

    @Schema(description = "治愈周期")
    private String period;

    @Schema(description = "治愈率")
    private String rate;

    @Schema(description = "治疗方法")
    private String treatment;
}
