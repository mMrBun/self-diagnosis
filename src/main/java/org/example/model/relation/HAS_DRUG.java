package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Disease;
import org.example.model.node.Drug;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病可用药品
 */
@RelationshipEntity(type = "HAS_DRUG")
@Data
public class HAS_DRUG {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Drug child;

    @Property
    private String name;
}
