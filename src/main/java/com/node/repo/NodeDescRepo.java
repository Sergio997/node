package com.node.repo;

import com.node.model.NodeDesc;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeDescRepo extends ReactiveMongoRepository<NodeDesc, String> {
}
