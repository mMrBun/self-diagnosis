package org.example.service.relation;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.AliasMapper;
import org.example.mapper.node.DiseaseMapper;
import org.example.mapper.relation.ALIAS_IS_Mapper;
import org.example.model.node.Alias;
import org.example.model.node.Disease;
import org.example.model.relation.ALIAS_IS;
import org.example.pojo.dto.req.relation.ALIAS_IS_ReqDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Slf4j
@Service
public class ALIAS_IS_Service {

    @Resource
    private ALIAS_IS_Mapper alias_is_mapper;

    @Resource
    private DiseaseMapper diseaseMapper;

    @Resource
    private AliasMapper aliasMapper;

    public ALIAS_IS addAliasRelation(ALIAS_IS_ReqDto dto) {
        Optional<Disease> startResult = diseaseMapper.findById(dto.getParentId());
        Optional<Alias> endResult = aliasMapper.findById(dto.getChildId());
        if(startResult==null){
            throw new BizException(ResponseCode.START_NODE_EMPTY_ERROR);
        }else if(endResult==null){
            throw new BizException(ResponseCode.END_NODE_EMPTY_ERROR);
        }else{
           boolean isExist =  alias_is_mapper.checkALIAS_IS_Relation(startResult.get().getId(),endResult.get().getId())>0;
           if(!isExist){
               ALIAS_IS entity = new ALIAS_IS();
               entity.setParent(startResult.get());
               entity.setChild(endResult.get());
               entity.setName(dto.getName());
               return alias_is_mapper.save(entity);
           }else{
               throw new BizException(ResponseCode.RELATION_EXIST_ERROR);
           }
        }
    }

    public boolean deleteAliasRelation(Long relationId) {
        try{
            alias_is_mapper.deleteById(relationId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }

    }
}
