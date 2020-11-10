package com.node.repo;

import com.node.model.NodeRoot;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRootRepo extends ReactiveMongoRepository<NodeRoot, String> {
}
