package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Alias;
import org.example.model.node.Disease;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病的别名
 */
@RelationshipEntity(type = "ALIAS_IS")
@Data
public class ALIAS_IS{

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Alias child;

    @Property
    private String name;
}

