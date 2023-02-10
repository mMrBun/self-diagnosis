package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Complication;
import org.example.model.node.Disease;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病的并发症
 */
@RelationshipEntity(type = "HAS_COMPLICATION")
@Data
public class HAS_COMPLICATION {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Complication child;

    @Property
    private String name;
}
