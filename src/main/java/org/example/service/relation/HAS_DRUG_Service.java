package org.example.service.relation;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.ComplicationMapper;
import org.example.mapper.node.DiseaseMapper;
import org.example.mapper.node.DrugMapper;
import org.example.mapper.relation.HAS_COMPLICATION_Mapper;
import org.example.mapper.relation.HAS_DRUG_Mapper;
import org.example.model.node.Alias;
import org.example.model.node.Disease;
import org.example.model.node.Drug;
import org.example.model.relation.ALIAS_IS;
import org.example.model.relation.HAS_DRUG;
import org.example.pojo.dto.req.relation.HAS_DRUG_ReqDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Slf4j
@Service
public class HAS_DRUG_Service {

    @Resource
    private HAS_DRUG_Mapper has_drug_mapper;

    @Resource
    private DrugMapper drugMapper;

    @Resource
    private DiseaseMapper diseaseMapper;
    public HAS_DRUG addDrugRelation(HAS_DRUG_ReqDto dto) {
        Optional<Disease> startResult = diseaseMapper.findById(dto.getParentId());
        Optional<Drug> endResult = drugMapper.findById(dto.getChildId());
        if(startResult==null){
            throw new BizException(ResponseCode.START_NODE_EMPTY_ERROR);
        }else if(endResult==null){
            throw new BizException(ResponseCode.END_NODE_EMPTY_ERROR);
        }else{
            boolean isExist =  has_drug_mapper.checkHAS_DRUG_Relation(startResult.get().getId(),endResult.get().getId())>0;
            if(!isExist){
                HAS_DRUG entity = new HAS_DRUG();
                entity.setParent(startResult.get());
                entity.setChild(endResult.get());
                entity.setName(dto.getName());
                return has_drug_mapper.save(entity);
            }else{
                throw new BizException(ResponseCode.RELATION_EXIST_ERROR);
            }
        }
    }

    public boolean deleteDrugRelation(Long relationId) {
        try{
            has_drug_mapper.deleteById(relationId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    public List<Disease> getDiseaseByName(String word) {
        return diseaseMapper.getDiseaseByName(word);
    }

    public List<Disease> getDiseaseByComplication(String word) {
        return diseaseMapper.getDiseaseByComplication(word);
    }

    public List<Disease> getDiseaseByAlias(String word) {
        return diseaseMapper.getDiseaseByAlias(word);
    }

    public List<Disease> getDiseaseBySymptom(String word) {
        return diseaseMapper.getDiseaseBySymptom(word);
    }

    public List<Drug> getDrugs(String word) {
        return has_drug_mapper.getDrugs(word);
    }
}
