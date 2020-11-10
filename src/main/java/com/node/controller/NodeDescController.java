package com.node.controller;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.service.NodeDescService;
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
@RequestMapping("node-desc")
@AllArgsConstructor
public class NodeDescController {

    private final NodeDescService nodeDescService;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeDescResponse> getNodeRootById(@PathVariable String id) {
        return nodeDescService.getNodeDeskById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET,
            path = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<NodeDescResponse> getAllNodeRoot() {
        return nodeDescService.getAllNodeDesk();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeDescResponse> createNodeRoot(@RequestBody NodeDescRequest rootRequest) {
        return nodeDescService.createNodeDesk(rootRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<NodeDescResponse> updateNodeRoot(@PathVariable String id, @RequestBody NodeDescRequest rootRequest) {
        return nodeDescService.updateNodeDesk(id, rootRequest);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE,
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Void> deleteNodeRootById(@PathVariable String id) {
        return nodeDescService.deleteNodeDesk(id);
    }
}
