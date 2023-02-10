package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Disease;
import org.example.model.node.Part;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病发病部位
 */
@RelationshipEntity(type = "PART_IS")
@Data
public class PART_IS {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Part child;

    @Property
    private String name;
}
