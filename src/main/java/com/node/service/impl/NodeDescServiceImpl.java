package com.node.service.impl;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.exception.NodeNotFoundException;
import com.node.mapper.NodeDescMapper;
import com.node.model.NodeDesc;
import com.node.repo.NodeDescRepo;
import com.node.service.NodeDescService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NodeDescServiceImpl implements NodeDescService {

    private final NodeDescRepo repo;
    private final NodeDescMapper mapper;

    @Override
    public Mono<NodeDescResponse> getNodeDeskById(String id) {
        Mono<NodeDesc> byId = repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")));
        return byId.map(nodeDesc -> new NodeDescResponse(nodeDesc.getName(), nodeDesc.getDescription()));
    }

    @Override
    public Flux<NodeDescResponse> getAllNodeDesk() {
        Flux<NodeDesc> allNodeRoot = repo.findAll()
                .switchIfEmpty(Flux.error(new NodeNotFoundException("Not found node")));
        return allNodeRoot.map(nodeDesc -> new NodeDescResponse(nodeDesc.getName(), nodeDesc.getDescription()));
    }

    @Override
    public Mono<NodeDescResponse> createNodeDesk(NodeDescRequest newNode) {
        Mono<NodeDesc> saveNodeRoot = null;
        if (newNode != null) {
            NodeDesc nodeDesc = mapper.requestToModel(newNode);
            saveNodeRoot = repo.save(nodeDesc);
            return saveNodeRoot.map(nd -> new NodeDescResponse(nd.getName(), nd.getDescription()));
        }
        throw new NodeNotFoundException("null object");
    }

    @Override
    public Mono<NodeDescResponse> updateNodeDesk(String id, NodeDescRequest nodeDescRequest) {
        NodeDesc oldNode = repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")))
                .block();
        NodeDesc updateNodeDesk= mapper.requestToEntity(nodeDescRequest, oldNode);
        Mono<NodeDesc> saveNodeRoot = repo.save(updateNodeDesk);
        return saveNodeRoot.map(nodeDesc -> new NodeDescResponse(nodeDesc.getName(), nodeDesc.getDescription()));
    }

    @Override
    public Mono<Void> deleteNodeDesk(String id) {
        repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")));
        return repo.deleteById(id);
    }

}
