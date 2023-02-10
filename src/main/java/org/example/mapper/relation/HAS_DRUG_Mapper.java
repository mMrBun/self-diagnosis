package org.example.mapper.relation;

import org.example.model.node.Disease;
import org.example.model.node.Drug;
import org.example.model.relation.HAS_DRUG;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface HAS_DRUG_Mapper extends Neo4jRepository<HAS_DRUG,Long> {

    @Query(value = "match(d:Disease)-[l:HAS_DRUG]->(a:Drug) where id(d)=$startNodeId and id(a)=$endNodeId return count(l)")
    int checkHAS_DRUG_Relation(Long startNodeId, Long endNodeId);


    @Query(value = "match(e:Disease)-[l:HAS_DRUG]->(d:Drug) where e.name=$word return d")
    List<Drug> getDrugs(String word);
}
