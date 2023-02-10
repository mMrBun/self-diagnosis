package org.example.model.node;

import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe 别名
 */
@NodeEntity(value = "Alias")
@Data
public class Alias {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "name")
    private String name;
}
