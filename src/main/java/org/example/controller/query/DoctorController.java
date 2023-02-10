package org.example.controller.query;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ApiResponse;
import org.example.service.DiseaseAnalysisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@RestController
@Slf4j
@Api(tags = "望闻问切")
@RequestMapping("/api/query")
public class DoctorController {

    @Resource
    private DiseaseAnalysisService diseaseAnalysisService;

    @ApiOperation(value = "您那里不舒服啊？")
    @GetMapping("/diseaseAnalysis")
    public ApiResponse diseaseAnalysis(@RequestParam(name = "describe") String describe){
        List<String> result = diseaseAnalysisService.diseaseAnalysis(describe);
        return ApiResponse.success(result);
    }
}
