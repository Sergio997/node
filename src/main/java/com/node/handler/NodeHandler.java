package com.node.handler;

import com.node.dto.request.NodeRequest;
import com.node.dto.response.NodeResponse;
import com.node.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class NodeHandler {
    private final NodeService service;

    public Mono<ServerResponse> getAllNode(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAllNode(), NodeResponse.class);
    }

    public Mono<ServerResponse> createNode(ServerRequest serverRequest) {
        Mono<NodeRequest> nodeMono = serverRequest.bodyToMono(NodeRequest.class);
        return nodeMono.flatMap(nodeRequest ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(service.createNode(nodeRequest.getNodeRoot(), nodeRequest.getNodeDesc()), NodeResponse.class));
    }

}
