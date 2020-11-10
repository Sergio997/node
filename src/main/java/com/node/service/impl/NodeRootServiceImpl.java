package com.node.service.impl;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.exception.NodeNotFoundException;
import com.node.mapper.NodeRootMapper;
import com.node.model.NodeRoot;
import com.node.repo.NodeRootRepo;
import com.node.service.NodeRootService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NodeRootServiceImpl implements NodeRootService {

    private final NodeRootRepo repo;
    private final NodeRootMapper mapper;

    @Override
    public Mono<NodeRootResponse> getNodeById(String id) {
        Mono<NodeRoot> byId = repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")));
        return byId.map(nodeRoot -> new NodeRootResponse(nodeRoot.getName()));
    }

    @Override
    public Flux<NodeRootResponse> getAllNode() {
        Flux<NodeRoot> allNodeRoot = repo.findAll()
                .switchIfEmpty(Flux.error(new NodeNotFoundException("Not found node")));
        return allNodeRoot.map(nodeRoot -> new NodeRootResponse(nodeRoot.getName()));
    }

    @Override
    public Mono<NodeRootResponse> createNode(NodeRootRequest newNode) {
        Mono<NodeRoot> saveNodeRoot = null;
        if (newNode != null) {
            NodeRoot nodeRoot = mapper.requestToModel(newNode);
            saveNodeRoot = repo.save(nodeRoot);
            return saveNodeRoot.map(nr -> new NodeRootResponse(nr.getName()));
        }
        throw new NodeNotFoundException("node is empty");
    }

    @Override
    public Mono<NodeRootResponse> updateNode(String id, NodeRootRequest nodeRootRequest) {
        NodeRoot oldNode = repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")))
                .block();
        NodeRoot updateNodeRoot = mapper.requestToEntity(nodeRootRequest, oldNode);
        return repo.save(updateNodeRoot)
                .map(nodeRoot -> new NodeRootResponse(nodeRoot.getName()));
    }

    @Override
    public Mono<Void> deleteNode(String id) {
        repo.findById(id)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node")));
        return repo.deleteById(id);
    }
}
