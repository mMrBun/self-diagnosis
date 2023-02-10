package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.DiseaseMapper;
import org.example.model.node.Disease;
import org.example.pojo.dto.req.node.DiseaseReqDto;
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
public class DiseaseService {

    @Resource
    private DiseaseMapper diseaseMapper;

    public Disease addDisease(DiseaseReqDto dto) {
        Disease entity = new Disease();
        BeanUtils.copyProperties(dto,entity);
        return diseaseMapper.save(entity);
    }

    public boolean deleteDisease(Long nodeId) {
        try{
            diseaseMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editDisease(Disease dto) {
        Disease resultEntity = diseaseMapper.save(dto);
        return resultEntity!=null;
    }
}
