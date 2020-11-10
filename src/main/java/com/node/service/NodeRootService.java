package com.node.service;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NodeRootService {
    Mono<NodeRootResponse> getNodeById(String id);

    Flux<NodeRootResponse> getAllNode();

    Mono<NodeRootResponse> createNode(NodeRootRequest newNode);

    Mono<NodeRootResponse> updateNode(String id, NodeRootRequest updateNode);

    Mono<Void> deleteNode(String id);
}
