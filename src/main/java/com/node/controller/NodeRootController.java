package com.node.controller;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.service.NodeRootService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("node-root")
@AllArgsConstructor
public class NodeRootController {

    private final NodeRootService nodeRootService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeRootResponse> getNodeRootById(@PathVariable String id) {
        return nodeRootService.getNodeById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<NodeRootResponse> getAllNodeRoot() {
        return nodeRootService.getAllNode();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeRootResponse> createNodeRoot(@RequestBody NodeRootRequest rootRequest) {
        return nodeRootService.createNode(rootRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeRootResponse> updateNodeRoot(@PathVariable String id, @RequestBody NodeRootRequest rootRequest) {
        return nodeRootService.updateNode(id, rootRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteNodeRootById(@PathVariable String id) {
        return nodeRootService.deleteNode(id);
    }
}
