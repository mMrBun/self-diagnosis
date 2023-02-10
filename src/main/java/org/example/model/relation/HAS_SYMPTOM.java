package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Disease;
import org.example.model.node.Symptom;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病症状
 */
@RelationshipEntity(type = "HAS_SYMPTOM")
@Data
public class HAS_SYMPTOM {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Symptom child;

    @Property
    private String name;
}
