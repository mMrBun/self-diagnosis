package org.example.model.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 疾病
 */
@Data
@NodeEntity(value = "Disease")
public class Disease {

    @Id
    @GeneratedValue
    private Long id;

    @Property(value = "age")
    private String age;

    @Property(value = "checklist")
    private String checklist;

    @Property(value = "infection")
    private String infection;

    @Property(value = "insurance")
    private String insurance;

    @Property(value = "money")
    private String money;

    @Property(value = "name")
    private String name;

    @Property(value = "period")
    private String period;

    @Property(value = "rate")
    private String rate;

    @Property(value = "treatment")
    private String treatment;
}
