package com.node.controller;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.model.NodeDesc;
import com.node.service.impl.NodeDescServiceImpl;
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
@WebFluxTest(controllers = NodeDescController.class)
public class NodeDescControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private NodeDescServiceImpl nodeDescService;

    private static final String ID = "Id";
    private static final String NAME = "Title";
    private static final String DESCRIPTION = "Text";

    private NodeDescResponse nodeDescResponse;
    private NodeDescRequest nodeDescRequest;
    private Mono<NodeDescResponse> nodeDescResponseMono;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeDescResponse = new NodeDescResponse() {{
            setName(NAME);
            setDescription(DESCRIPTION);
        }};

        nodeDescResponseMono = Mono.just(nodeDescResponse);

        nodeDescRequest = new NodeDescRequest() {{
            setName(NAME);
            setDescription(DESCRIPTION);
        }};
    }

    @Test
    public void getNodeDescById() {
        String inputId = "69696";

        Mockito.when(nodeDescService.getNodeDeskById(inputId))
                .thenReturn(nodeDescResponseMono);

        webTestClient.get()
                .uri("/node-desc/{id}", inputId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    public void testCreateNodeDesc() {

        Mockito.when(nodeDescService.createNodeDesk(nodeDescRequest)).thenReturn(nodeDescResponseMono);

        webTestClient.post()
                .uri("/node-desc")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(nodeDescRequest), NodeDescRequest.class)
                .exchange()
                .expectStatus().isCreated();

    }
}
