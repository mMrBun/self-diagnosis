package org.example.mapper.relation;

import org.example.model.relation.HAS_COMPLICATION;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface HAS_COMPLICATION_Mapper extends Neo4jRepository<HAS_COMPLICATION,Long> {

    @Query(value = "match(d:Disease)-[l:HAS_COMPLICATION]->(a:Complication) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkHAS_COMPLICATION_Relation(Long startNodeId, Long endNodeId);
}
