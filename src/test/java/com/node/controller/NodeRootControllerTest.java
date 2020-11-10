package com.node.controller;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.service.impl.NodeRootServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = NodeRootController.class)
public class NodeRootControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NodeRootServiceImpl nodeRootService;

    private static final String ID = "Id";
    private static final String NAME = "Title";

    private NodeRootResponse nodeRootResponse;
    private NodeRootRequest nodeRootRequest;
    private Mono<NodeRootResponse> nodeRootResponseMono;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeRootResponse = new NodeRootResponse() {{
            setName(NAME);
        }};

        nodeRootResponseMono = Mono.just(nodeRootResponse);

        nodeRootRequest = new NodeRootRequest() {{
            setName(NAME);
        }};
    }

    @Test
    public void getNodeRootById() {
        String inputId = "69696";

        Mockito.when(nodeRootService.getNodeById(inputId))
                .thenReturn(nodeRootResponseMono);

        webTestClient.get()
                .uri("/node-root/{id}", inputId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void testCreateNodeRoot() {

        Mockito.when(nodeRootService.createNode(nodeRootRequest)).thenReturn(nodeRootResponseMono);

        webTestClient.post()
                .uri("/node-root")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(nodeRootRequest), NodeRootRequest.class)
                .exchange()
                .expectStatus().isCreated();

    }
}
