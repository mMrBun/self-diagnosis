package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.SymptomMapper;
import org.example.model.node.Symptom;
import org.example.pojo.dto.req.node.SymptomReqDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Service
@Slf4j
public class SymptomService {

    @Resource
    private SymptomMapper symptomMapper;

    public Symptom addSymptom(SymptomReqDto dto) {
        Symptom entity = new Symptom();
        BeanUtils.copyProperties(dto,entity);
       return symptomMapper.save(entity);
    }

    public boolean deleteSymptom(Long nodeId) {
        try{
            symptomMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editSymptom(Symptom dto) {
        Symptom resultEntity = symptomMapper.save(dto);
        return resultEntity!=null;
    }
}
