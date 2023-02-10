package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.DrugMapper;
import org.example.model.node.Drug;
import org.example.pojo.dto.req.node.DrugReqDto;
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
public class DrugService {

    @Resource
    private DrugMapper drugMapper;

    public Drug addDrug(DrugReqDto dto) {
        Drug entity = new Drug();
        BeanUtils.copyProperties(dto,entity);
        return drugMapper.save(entity);
    }

    public boolean deleteDrug(Long nodeId) {
        try{
            drugMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editDrug(Drug dto) {
        Drug resultEntity = drugMapper.save(dto);
        return resultEntity!=null;
    }
}
