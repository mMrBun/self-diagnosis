package org.example.controller.basic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.model.node.*;
import org.example.pojo.ApiResponse;
import org.example.pojo.dto.req.node.*;
import org.example.service.node.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@RestController
@Slf4j
@RequestMapping("/api/node")
@Api(tags = "节点基本操作接口")
public class NodeController {

    @Resource
    private AliasService aliasService;

    @Resource
    private DiseaseService diseaseService;

    @Resource
    private ComplicationService complicationService;

    @Resource
    private DrugService drugService;

    @Resource
    private DepartmentService departmentService;

    @Resource
    private PartService partService;

    @Resource
    private SymptomService symptomService;

    @PostMapping("addDisease")
    @ApiOperation(value = "创建疾病节点")
    public ApiResponse addDisease(@RequestBody DiseaseReqDto dto){
        Disease result = diseaseService.addDisease(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteDisease")
    @ApiOperation(value = "删除疾病节点")
    public ApiResponse deleteDisease(Long nodeId){
        boolean flag = diseaseService.deleteDisease(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editDisease")
    @ApiOperation(value = "修改疾病节点")
    public ApiResponse editDisease(@RequestBody Disease dto){
        boolean flag = diseaseService.editDisease(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addAlias")
    @ApiOperation(value = "创建别名节点")
    public ApiResponse addAlias(@RequestBody AliasReqDto dto){
        Alias result = aliasService.addAlias(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteAlias")
    @ApiOperation(value = "删除别名节点")
    public ApiResponse deleteAlias(Long nodeId){
        boolean flag = aliasService.deleteAlias(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editAlias")
    @ApiOperation(value = "修改别名节点")
    public ApiResponse editAlias(@RequestBody Alias dto){
        boolean flag = aliasService.editAlias(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addComplication")
    @ApiOperation(value = "创建并发症节点")
    public ApiResponse addComplication(@RequestBody ComplicationReqDto dto){
        Complication result = complicationService.addComplication(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteComplication")
    @ApiOperation(value = "删除并发症节点")
    public ApiResponse deleteComplication(Long nodeId){
        boolean flag = complicationService.deleteComplication(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editComplication")
    @ApiOperation(value = "修改并发症节点")
    public ApiResponse editComplication(@RequestBody Complication dto){
        boolean flag = complicationService.editComplication(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addDepartment")
    @ApiOperation(value = "创建科室节点")
    public ApiResponse addDepartment(@RequestBody DepartmentReqDto dto){
        Department result = departmentService.addDepartment(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteDepartment")
    @ApiOperation(value = "删除科室节点")
    public ApiResponse deleteDepartment(Long nodeId){
        boolean flag = departmentService.deleteDepartment(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editDepartment")
    @ApiOperation(value = "修改科室节点")
    public ApiResponse editDepartment(@RequestBody Department dto){
        boolean flag = departmentService.editDepartment(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addDrug")
    @ApiOperation(value = "创建药品节点")
    public ApiResponse addDrug(@RequestBody DrugReqDto dto){
        Drug result = drugService.addDrug(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteDrug")
    @ApiOperation(value = "删除药品节点")
    public ApiResponse deleteDrug(Long nodeId){
        boolean flag = drugService.deleteDrug(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editDrug")
    @ApiOperation(value = "修改药品节点")
    public ApiResponse editDrug(@RequestBody Drug dto){
        boolean flag = drugService.editDrug(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addPart")
    @ApiOperation(value = "创建发病部位节点")
    public ApiResponse addPart(@RequestBody PartReqDto dto){
        Part result = partService.addPart(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deletePart")
    @ApiOperation(value = "删除发病部位节点")
    public ApiResponse deletePart(Long nodeId){
        boolean flag = partService.deletePart(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editPart")
    @ApiOperation(value = "修改发病部位节点")
    public ApiResponse editPart(@RequestBody Part dto){
        boolean flag = partService.editPart(dto);
        return ApiResponse.success(flag);
    }

    @PostMapping("addSymptom")
    @ApiOperation(value = "创建症状节点")
    public ApiResponse addSymptom(@RequestBody SymptomReqDto dto){
        Symptom result = symptomService.addSymptom(dto);
        return ApiResponse.success(result);
    }

    @DeleteMapping("deleteSymptom")
    @ApiOperation(value = "删除症状节点")
    public ApiResponse deleteSymptom(Long nodeId){
        boolean flag = symptomService.deleteSymptom(nodeId);
        return ApiResponse.success(flag);
    }

    @PutMapping("editSymptom")
    @ApiOperation(value = "修改症状节点")
    public ApiResponse editSymptom(@RequestBody Symptom dto){
        boolean flag = symptomService.editSymptom(dto);
        return ApiResponse.success(flag);
    }
}
