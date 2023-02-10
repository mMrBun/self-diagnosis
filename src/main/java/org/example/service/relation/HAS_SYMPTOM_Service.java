package org.example.service.relation;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.ComplicationMapper;
import org.example.mapper.node.DiseaseMapper;
import org.example.mapper.node.SymptomMapper;
import org.example.mapper.relation.HAS_COMPLICATION_Mapper;
import org.example.mapper.relation.HAS_SYMPTOM_Mapper;
import org.example.model.node.Alias;
import org.example.model.node.Disease;
import org.example.model.node.Symptom;
import org.example.model.relation.ALIAS_IS;
import org.example.model.relation.HAS_SYMPTOM;
import org.example.pojo.dto.req.relation.HAS_SYMPTOM_ReqDto;
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
public class HAS_SYMPTOM_Service {

    @Resource
    private HAS_SYMPTOM_Mapper has_symptom_mapper;

    @Resource
    private SymptomMapper symptomMapper;

    @Resource
    private DiseaseMapper diseaseMapper;
    public HAS_SYMPTOM addSymptomRelation(HAS_SYMPTOM_ReqDto dto) {
        Optional<Disease> startResult = diseaseMapper.findById(dto.getParentId());
        Optional<Symptom> endResult = symptomMapper.findById(dto.getChildId());
        if(startResult==null){
            throw new BizException(ResponseCode.START_NODE_EMPTY_ERROR);
        }else if(endResult==null){
            throw new BizException(ResponseCode.END_NODE_EMPTY_ERROR);
        }else{
            boolean isExist =  has_symptom_mapper.checkHAS_SYMPTOM_Relation(startResult.get().getId(),endResult.get().getId())>0;
            if(!isExist){
                HAS_SYMPTOM entity = new HAS_SYMPTOM();
                entity.setParent(startResult.get());
                entity.setChild(endResult.get());
                entity.setName(dto.getName());
                return has_symptom_mapper.save(entity);
            }else{
                throw new BizException(ResponseCode.RELATION_EXIST_ERROR);
            }
        }
    }

    public boolean deleteSymptomRelation(Long relationId) {
        try{
            has_symptom_mapper.deleteById(relationId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    public List<Symptom> getSymptoms(String word) {
        return has_symptom_mapper.getSymptoms(word);
    }
}
