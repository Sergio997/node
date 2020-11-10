package com.node.service.impl;

import com.node.dto.response.NodeResponse;
import com.node.exception.NodeNotFoundException;
import com.node.model.Node;
import com.node.model.NodeDesc;
import com.node.model.NodeRoot;
import com.node.repo.NodeDescRepo;
import com.node.repo.NodeRepo;
import com.node.repo.NodeRootRepo;
import com.node.service.NodeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NodeServiceImpl implements NodeService {

    private final NodeRepo nodeRepo;
    private final NodeRootRepo nodeRootRepo;
    private final NodeDescRepo nodeDescRepo;

    @Override
    public Flux<NodeResponse> getAllNode() {
        return nodeRepo.findAll()
                .map(node -> new NodeResponse(node.getNodeRoot(), node.getNodeDesc()));
    }

    @Override
    public Mono<NodeResponse> createNode(String idNodeRoot, String idNodeDesc) {
        nodeRootRepo.findById(idNodeRoot)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node root")));
        nodeDescRepo.findById(idNodeDesc)
                .switchIfEmpty(Mono.error(new NodeNotFoundException("Not found node desk")));
        Node node = new Node(idNodeRoot, idNodeDesc);
        Mono<Node> save = nodeRepo.save(node);
        return nodeRepo.save(node).map(n -> new NodeResponse(n.getNodeRoot(), n.getNodeDesc()));
    }

}
