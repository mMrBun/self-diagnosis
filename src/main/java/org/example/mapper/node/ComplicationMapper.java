package org.example.mapper.node;

import org.example.model.node.Complication;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @author XUHUIK
 * @create 2023/2/7
 * @describe functional_description
 */
@Repository
public interface ComplicationMapper extends Neo4jRepository<Complication,Long> {
}
