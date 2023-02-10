package org.example.service.relation;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.ComplicationMapper;
import org.example.mapper.node.DepartmentMapper;
import org.example.mapper.node.DiseaseMapper;
import org.example.mapper.relation.DEPARTMENT_IS_Mapper;
import org.example.mapper.relation.HAS_COMPLICATION_Mapper;
import org.example.model.node.Alias;
import org.example.model.node.Complication;
import org.example.model.node.Disease;
import org.example.model.relation.ALIAS_IS;
import org.example.model.relation.HAS_COMPLICATION;
import org.example.pojo.dto.req.relation.HAS_COMPLICATION_ReqDto;
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
public class HAS_COMPLICATION_Service {

    @Resource
    private HAS_COMPLICATION_Mapper has_complication_mapper;

    @Resource
    private ComplicationMapper complicationMapper;

    @Resource
    private DiseaseMapper diseaseMapper;

    public HAS_COMPLICATION addComplicationRelation(HAS_COMPLICATION_ReqDto dto) {
        Optional<Disease> startResult = diseaseMapper.findById(dto.getParentId());
        Optional<Complication> endResult = complicationMapper.findById(dto.getChildId());
        if(startResult==null){
            throw new BizException(ResponseCode.START_NODE_EMPTY_ERROR);
        }else if(endResult==null){
            throw new BizException(ResponseCode.END_NODE_EMPTY_ERROR);
        }else{
            boolean isExist =  has_complication_mapper.checkHAS_COMPLICATION_Relation(startResult.get().getId(),endResult.get().getId())>0;
            if(!isExist){
                HAS_COMPLICATION entity = new HAS_COMPLICATION();
                entity.setParent(startResult.get());
                entity.setChild(endResult.get());
                entity.setName(dto.getName());
                return has_complication_mapper.save(entity);
            }else{
                throw new BizException(ResponseCode.RELATION_EXIST_ERROR);
            }
        }
    }

    public boolean deleteComplicationRelation(Long relationId) {
        try{
            has_complication_mapper.deleteById(relationId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }
}
