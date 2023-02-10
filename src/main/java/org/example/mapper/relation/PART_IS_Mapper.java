package org.example.mapper.relation;

import org.example.model.relation.PART_IS;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface PART_IS_Mapper extends Neo4jRepository<PART_IS,Long> {

    @Query(value = "match(d:Disease)-[l:PART_IS]->(a:Part) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkPART_IS_Relation(Long startNodeId, Long endNodeId);
}
