package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.PartMapper;
import org.example.model.node.Part;
import org.example.pojo.dto.req.node.PartReqDto;
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
public class PartService {

    @Resource
    private PartMapper partMapper;

    public Part addPart(PartReqDto dto) {
        Part entity = new Part();
        BeanUtils.copyProperties(dto,entity);
        return partMapper.save(entity);
    }

    public boolean deletePart(Long nodeId) {
        try{
            partMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editPart(Part dto) {
        Part resultEntity = partMapper.save(dto);
        return resultEntity!=null;
    }
}
