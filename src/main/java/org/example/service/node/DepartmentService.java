package org.example.service.node;

import lombok.extern.slf4j.Slf4j;
import org.example.execption.BizException;
import org.example.execption.ResponseCode;
import org.example.mapper.node.DepartmentMapper;
import org.example.model.node.Department;
import org.example.pojo.dto.req.node.DepartmentReqDto;
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
public class DepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    public Department addDepartment(DepartmentReqDto dto) {
        Department entity = new Department();
        BeanUtils.copyProperties(dto,entity);
        return departmentMapper.save(entity);
    }

    public boolean deleteDepartment(Long nodeId) {
        try{
            departmentMapper.deleteById(nodeId);
            return true;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new BizException(ResponseCode.HAS_RELATION_ERROR);
        }
    }

    public boolean editDepartment(Department dto) {
        Department resultEntity = departmentMapper.save(dto);
        return resultEntity!=null;
    }
}
