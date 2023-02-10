package org.example.execption;

import lombok.Getter;

@Getter
public enum ResponseCode {
    START_NODE_EMPTY_ERROR(1001,"起始节点为空，请先创建起始节点"),
    END_NODE_EMPTY_ERROR(1002,"末尾节点为空，请先创建末尾节点"),
    ERROR_19(1003, "请求过快"),
    NODE_EXIST_ERROR(1004, "创建节点已存在，请检查节点信息后重新创建"),
    RELATION_EXIST_ERROR(1005, "开始节点到末尾节点关系已存在，请检查关系信息后重新创建"),
    HAS_RELATION_ERROR(1006, "节点含有关联关系，请先删除关联关系后重试"),
    QUESTION_DOS_NOT_KNOW_ERROR(1007, "库中无相关数据或意图表达不明确");

    private Integer code;

    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
