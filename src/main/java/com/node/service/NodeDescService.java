package com.node.service;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NodeDescService {
    Mono<NodeDescResponse> getNodeDeskById(String id);

    Flux<NodeDescResponse> getAllNodeDesk();

    Mono<NodeDescResponse> createNodeDesk(NodeDescRequest newNode);

    Mono<NodeDescResponse> updateNodeDesk(String id, NodeDescRequest updateNode);

    Mono<Void> deleteNodeDesk(String id);
}
