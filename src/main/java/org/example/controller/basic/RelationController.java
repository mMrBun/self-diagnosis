package org.example.controller.basic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.relation.*;
import org.example.pojo.ApiResponse;
import org.example.pojo.dto.req.relation.*;
import org.example.service.relation.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@RestController
@Api(tags = "关系基本操作接口")
@RequestMapping("/api/relation")
public class RelationController {

    @Resource
    private ALIAS_IS_Service alias_is_service;

    @Resource
    private DEPARTMENT_IS_Service department_is_service;

    @Resource
    private HAS_COMPLICATION_Service has_complication_service;

    @Resource
    private HAS_DRUG_Service has_drug_service;

    @Resource
    private HAS_SYMPTOM_Service has_symptom_service;

    @Resource
    private PART_IS_Service part_is_service;

    @ApiOperation(value = "创建别名关系")
    @PostMapping("addAliasRelation")
    public ApiResponse addAliasRelation(@RequestBody ALIAS_IS_ReqDto dto){
        ALIAS_IS result = alias_is_service.addAliasRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "创建科室关系")
    @PostMapping("addDepartmentRelation")
    public ApiResponse addDepartmentRelation(@RequestBody DEPARTMENT_IS_ReqDto dto){
        DEPARTMENT_IS result = department_is_service.addDepartmentRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "创建并发症关系")
    @PostMapping("addComplicationRelation")
    public ApiResponse addComplicationRelation(@RequestBody HAS_COMPLICATION_ReqDto dto){
        HAS_COMPLICATION result = has_complication_service.addComplicationRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "创建药品关系")
    @PostMapping("addDrugRelation")
    public ApiResponse addDrugRelation(@RequestBody HAS_DRUG_ReqDto dto){
        HAS_DRUG result = has_drug_service.addDrugRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "创建症状关系")
    @PostMapping("addSymptomRelation")
    public ApiResponse addSymptomRelation(@RequestBody HAS_SYMPTOM_ReqDto dto){
        HAS_SYMPTOM result = has_symptom_service.addSymptomRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "创建发病部位关系")
    @PostMapping("addPartRelation")
    public ApiResponse addPartRelation(@RequestBody PART_IS_ReqDto dto){
        PART_IS result = part_is_service.addPartRelation(dto);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除别名关系")
    @DeleteMapping("deleteAliasRelation")
    public ApiResponse deleteAliasRelation(Long relationId){
        boolean result = alias_is_service.deleteAliasRelation(relationId);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除科室关系")
    @DeleteMapping("deleteDepartmentRelation")
    public ApiResponse deleteDepartmentRelation(Long relationId){
        boolean result = department_is_service.deleteDepartmentRelation(relationId);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除并发症关系")
    @DeleteMapping("deleteComplicationRelation")
    public ApiResponse deleteComplicationRelation(Long relationId){
        boolean result = has_complication_service.deleteComplicationRelation(relationId);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除药品关系")
    @DeleteMapping("deleteDrugRelation")
    public ApiResponse deleteDrugRelation(Long relationId){
        boolean result = has_drug_service.deleteDrugRelation(relationId);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除症状关系")
    @DeleteMapping("deleteSymptomRelation")
    public ApiResponse deleteSymptomRelation(Long relationId){
        boolean result = has_symptom_service.deleteSymptomRelation(relationId);
        return ApiResponse.success(result);
    }

    @ApiOperation(value = "删除发病部位关系")
    @DeleteMapping("deletePartRelation")
    public ApiResponse deletePartRelation(Long relationId){
        boolean result = part_is_service.deletePartRelation(relationId);
        return ApiResponse.success(result);
    }
    
    


}
