package org.example.service.relation;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.DepartmentMapper;
import org.example.mapper.node.DiseaseMapper;
import org.example.mapper.relation.DEPARTMENT_IS_Mapper;
import org.example.model.node.Alias;
import org.example.model.node.Department;
import org.example.model.node.Disease;
import org.example.model.relation.ALIAS_IS;
import org.example.model.relation.DEPARTMENT_IS;
import org.example.pojo.dto.req.relation.DEPARTMENT_IS_ReqDto;
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
public class DEPARTMENT_IS_Service {

    @Resource
    private DEPARTMENT_IS_Mapper department_is_mapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private DiseaseMapper diseaseMapper;

    public DEPARTMENT_IS addDepartmentRelation(DEPARTMENT_IS_ReqDto dto) {
        Optional<Disease> startResult = diseaseMapper.findById(dto.getParentId());
        Optional<Department> endResult = departmentMapper.findById(dto.getChildId());
        if(startResult==null){
            throw new BizException(ResponseCode.START_NODE_EMPTY_ERROR);
        }else if(endResult==null){
            throw new BizException(ResponseCode.END_NODE_EMPTY_ERROR);
        }else{
            boolean isExist =  department_is_mapper.checkDEPARTMENT_IS_Relation(startResult.get().getId(),endResult.get().getId())>0;
            if(!isExist){
                DEPARTMENT_IS entity = new DEPARTMENT_IS();
                entity.setParent(startResult.get());
                entity.setChild(endResult.get());
                entity.setName(dto.getName());
                return department_is_mapper.save(entity);
            }else{
                throw new BizException(ResponseCode.RELATION_EXIST_ERROR);
            }
        }
    }

    public boolean deleteDepartmentRelation(Long relationId) {
        try{
            department_is_mapper.deleteById(relationId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return false;
        }
    }

    public List<Department> getDepartments(String word) {
        return department_is_mapper.getDepartments(word);
    }
}
