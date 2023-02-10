package org.example.model.relation;


import lombok.Data;
import org.example.model.node.Department;
import org.example.model.node.Disease;
import org.neo4j.ogm.annotation.*;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病所属科室
 */
@RelationshipEntity(type = "DEPARTMENT_IS")
@Data
public class DEPARTMENT_IS {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Disease parent;

    @EndNode
    private Department child;

    @Property
    private String name;
}
