package org.example.mapper.relation;

import org.example.model.relation.ALIAS_IS;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface ALIAS_IS_Mapper extends Neo4jRepository<ALIAS_IS,Long> {

    /**
     * 检查别名关系是否存在
     * @param startNodeId
     * @param endNodeId
     * @return
     */
    @Query(value = "match(d:Disease)-[l:ALIAS_IS]->(a:Alias) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkALIAS_IS_Relation(Long startNodeId, Long endNodeId);
}
