package com.node.service;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.mapper.NodeRootMapper;
import com.node.model.NodeRoot;
import com.node.repo.NodeRootRepo;
import com.node.service.impl.NodeRootServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NodeRootServiceTest {

    @InjectMocks
    private NodeRootServiceImpl testObj;
    @Mock
    private NodeRootRepo repoMock;
    @Mock
    private NodeRootMapper mapperMock;

    private static final String ID = "Id";
    private static final String NAME = "Title";

    private NodeRoot nodeRoot;
    private NodeRootResponse nodeRootResponse;
    private NodeRootRequest nodeRootRequest;
    private Mono<NodeRoot> nodeRootMono;
    private Mono<NodeRootResponse> nodeRootResponseMono;
    private Flux<NodeRoot> nodeRootFlux;
    private Mono<Void> voidMono;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeRoot = new NodeRoot() {{
            setId(ID);
            setName(NAME);
        }};

        nodeRootMono = Mono.just(nodeRoot);
        nodeRootFlux = Flux.just(nodeRoot);

        nodeRootResponse = new NodeRootResponse() {{
            setName(NAME);
        }};

        nodeRootResponseMono = Mono.just(nodeRootResponse);

        nodeRootRequest = new NodeRootRequest() {{
            setName(NAME);
        }};
    }

    @Test
    public void getNodeDeskById() {
        // given
        Mockito.when(repoMock.findById(ArgumentMatchers.eq(ID)))
                .thenReturn(nodeRootMono);

        // when
        Mono<NodeRootResponse> nodeById = testObj.getNodeById(ID);

        // then
        Mockito.verify(repoMock).findById(ID);
        assertThat(nodeById).isNotNull();
    }

    @Test
    public void getNodeDeskById_WhenNullPointerException() {
        // given
        Mockito.when(repoMock.findById(ArgumentMatchers.eq(ID)))
                .thenReturn(nodeRootMono);

        // when
        assertThrows(NullPointerException.class, () ->
                testObj.getNodeById("id12231"));
    }

    @Test
    public void getAllNodeDesk() {
        // given
        Mockito.when(repoMock.findAll())
                .thenReturn(nodeRootFlux);

        // when
        Flux<NodeRootResponse> allNode = testObj.getAllNode();

        // then
        Mockito.verify(repoMock).findAll();
        assertThat(allNode).isNotNull();
    }

    @Test
    public void create() {
        Mockito.when(mapperMock.requestToModel(nodeRootRequest))
                .thenReturn(nodeRoot);
        Mockito.when(repoMock.save(nodeRoot))
                .thenReturn(nodeRootMono);

        Mono<NodeRootResponse> node = testObj.createNode(nodeRootRequest);

        Mockito.verify(mapperMock)
                .requestToModel(nodeRootRequest);
        Mockito.verify(repoMock)
                .save(nodeRoot);
        assertThat(node).isNotNull();
    }

    @Test
    public void update() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeRootMono);
        Mockito.when(mapperMock.requestToEntity(nodeRootRequest, nodeRoot))
                .thenReturn(nodeRoot);
        Mockito.when(repoMock.save(nodeRoot))
                .thenReturn(nodeRootMono);

        Mono<NodeRootResponse> nodeRootResponseMono = testObj.updateNode(ID, nodeRootRequest);

        Mockito.verify(repoMock)
                .findById(ID);
        Mockito.verify(mapperMock)
                .requestToEntity(nodeRootRequest, nodeRoot);
        Mockito.when(repoMock.save(nodeRoot))
                .thenReturn(nodeRootMono);
        assertThat(nodeRootResponseMono).isNotNull();
    }

    @Test
    public void updateWithException() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeRootMono);
        Mockito.when(mapperMock.requestToEntity(nodeRootRequest, nodeRoot))
                .thenReturn(nodeRoot);
        Mockito.when(repoMock.save(nodeRoot))
                .thenReturn(nodeRootMono);

        assertThrows(NullPointerException.class, () ->
                testObj.updateNode("id12231", nodeRootRequest));
    }

    @Test
    public void deleteNodeDesk() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeRootMono);
        Mockito.when(repoMock.deleteById(ID))
                .thenReturn(voidMono);

        // when
        testObj.deleteNode(ID);

        Mockito.verify(repoMock)
                .findById(ID);
        Mockito.verify(repoMock)
                .deleteById(ID);
    }

    @Test
    public void deleteWithException() {
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeRootMono);
        Mockito.when(repoMock.deleteById(ID))
                .thenReturn(voidMono);

        assertThrows(NullPointerException.class, () ->
                testObj.deleteNode("id12231"));
    }
}
