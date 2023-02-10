package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.AliasMapper;
import org.example.model.node.Alias;
import org.example.pojo.dto.req.node.AliasReqDto;
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
public class AliasService {

    @Resource
    private AliasMapper aliasMapper;

    public Alias addAlias(AliasReqDto dto) {
        Alias entity = new Alias();
        BeanUtils.copyProperties(dto,entity);
        return aliasMapper.save(entity);

    }

    public boolean deleteAlias(Long nodeId) {
        try{
            aliasMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editAlias(Alias dto) {
        Alias resultEntity = aliasMapper.save(dto);
        return resultEntity!=null;
    }
}
