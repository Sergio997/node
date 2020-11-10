package com.node.repo;

import com.node.model.Node;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepo extends ReactiveMongoRepository<Node, String> {
}
