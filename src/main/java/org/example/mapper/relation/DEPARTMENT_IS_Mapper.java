package org.example.mapper.relation;

import org.example.model.node.Department;
import org.example.model.relation.DEPARTMENT_IS;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface DEPARTMENT_IS_Mapper extends Neo4jRepository<DEPARTMENT_IS,Long> {

    @Query(value = "match(d:Disease)-[l:DEPARTMENT_IS]->(a:Department) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkDEPARTMENT_IS_Relation(Long startNodeId, Long endNodeId);

    @Query(value = "match(e:Disease)-[l:DEPARTMENT_IS]->(d:Department) where e.name=$word return d")
    List<Department> getDepartments(String word);
}
