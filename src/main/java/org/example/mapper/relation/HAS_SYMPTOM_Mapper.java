package org.example.mapper.relation;

import org.example.model.node.Symptom;
import org.example.model.relation.HAS_SYMPTOM;
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
public interface HAS_SYMPTOM_Mapper extends Neo4jRepository<HAS_SYMPTOM,Long> {

    @Query(value = "match(d:Disease)-[l:HAS_SYMPTOM]->(a:Symptom) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkHAS_SYMPTOM_Relation(Long startNodeId, Long endNodeId);

    @Query(value = "match(e:Disease)-[l:HAS_SYMPTOM]->(d:Symptom) where e.name=$word return d")
    List<Symptom> getSymptoms(String word);
}
