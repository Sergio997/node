package com.node.service;

import com.node.dto.response.NodeResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NodeService {

    Flux<NodeResponse> getAllNode();

    Mono<NodeResponse> createNode(String idNodeRoot, String idNodeDesc);

}
