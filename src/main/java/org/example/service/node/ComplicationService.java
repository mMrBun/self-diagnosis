package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.ComplicationMapper;
import org.example.model.node.Complication;
import org.example.pojo.dto.req.node.ComplicationReqDto;
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
public class ComplicationService {

    @Resource
    private ComplicationMapper complicationMapper;

    public Complication addComplication(ComplicationReqDto dto) {
        Complication entity = new Complication();
        BeanUtils.copyProperties(dto,entity);
        return complicationMapper.save(entity);
    }

    public boolean deleteComplication(Long nodeId) {
        try{
            complicationMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editComplication(Complication dto) {
        Complication resultEntity = complicationMapper.save(dto);
        return resultEntity!=null;
    }
}
