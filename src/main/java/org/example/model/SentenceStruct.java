package org.example.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author XUHUIK
 * @create 2023/2/8
 * @describe functional_description
 */
@Data
public class SentenceStruct {

    @Schema(description = "分词")
    private String word;

    @Schema(description = "词性")
    private String pos;

    @Schema(description = "命名实体")
    private String ne;

    @Schema(description = "词形")
    private String lemma;

}
