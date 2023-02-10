package org.example.mapper.node;

import org.example.model.node.Disease;
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
public interface DiseaseMapper extends Neo4jRepository<Disease,Long> {

//    @Query(value = "match(e:Disease) where e.:#{property} = :#{value} return e)")
//    Disease getDiseaseAttributes(String entityType,@Param("property") String property,@Param("value") String value);

    @Query(value = "match(e:Disease) where e.name =$word return e")
    List<Disease> getDiseaseByName(String word);

    @Query(value = "match(e:Complication)<-[l:HAS_COMPLICATION]-(d:Disease) where e.name=$word return d")
    List<Disease> getDiseaseByComplication(String word);

    @Query(value = "match(e:Alias)<-[l:ALIAS_IS]-(d:Disease) where e.name=$word return d")
    List<Disease> getDiseaseByAlias(String word);

    @Query(value = "match(e:Symptom)<-[l:HAS_SYMPTOM]-(d:Disease) where e.name=$word return d")
    List<Disease> getDiseaseBySymptom(String word);
}
