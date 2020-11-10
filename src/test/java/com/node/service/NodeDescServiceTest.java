package com.node.service;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.mapper.NodeDescMapper;
import com.node.model.NodeDesc;
import com.node.repo.NodeDescRepo;
import com.node.service.impl.NodeDescServiceImpl;
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


public class NodeDescServiceTest {

    @InjectMocks
    private NodeDescServiceImpl testObj;
    @Mock
    private NodeDescRepo repoMock;
    @Mock
    private NodeDescMapper mapperMock;

    private static final String ID = "Id";
    private static final String NAME = "Title";
    private static final String DESCRIPTION = "Text";

    private NodeDesc nodeDesc;
    private NodeDescResponse nodeDescResponse;
    private NodeDescRequest nodeDescRequest;
    private Mono<NodeDesc> nodeDescMono;
    private Mono<NodeDescResponse> nodeDescResponseMono;
    private Flux<NodeDesc> nodeDescFlux;
    private Mono<Void> voidMono;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeDesc = new NodeDesc() {{
            setId(ID);
            setName(NAME);
            setDescription(DESCRIPTION);
        }};

        nodeDescMono = Mono.just(nodeDesc);
        nodeDescFlux = Flux.just(nodeDesc);

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
    public void getNodeDeskById() {
        // given
        Mockito.when(repoMock.findById(ArgumentMatchers.eq(ID)))
                .thenReturn(nodeDescMono);

        // when
        Mono<NodeDescResponse> nodeDeskById = testObj.getNodeDeskById(ID);

        // then
        Mockito.verify(repoMock).findById(ID);
        assertThat(nodeDeskById).isNotNull();
    }

    @Test
    public void getNodeDeskById_WhenNullPointerException() {
        // given
        Mockito.when(repoMock.findById(ArgumentMatchers.eq(ID)))
                .thenReturn(nodeDescMono);

        // when
        assertThrows(NullPointerException.class, () ->
                testObj.getNodeDeskById("id12231"));
    }

    @Test
    public void getAllNodeDesk() {
        // given
        Mockito.when(repoMock.findAll())
                .thenReturn(nodeDescFlux);

        // when
        Flux<NodeDescResponse> allNodeDesk = testObj.getAllNodeDesk();

        // then
        Mockito.verify(repoMock).findAll();
        assertThat(allNodeDesk).isNotNull();
    }

    @Test
    public void create() {
        Mockito.when(mapperMock.requestToModel(nodeDescRequest))
                .thenReturn(nodeDesc);
        Mockito.when(repoMock.save(nodeDesc))
                .thenReturn(nodeDescMono);

        Mono<NodeDescResponse> nodeDesk = testObj.createNodeDesk(nodeDescRequest);

        Mockito.verify(mapperMock)
                .requestToModel(nodeDescRequest);
        Mockito.verify(repoMock)
                .save(nodeDesc);
        assertThat(nodeDesk).isNotNull();
    }

    @Test
    public void update() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeDescMono);
        Mockito.when(mapperMock.requestToEntity(nodeDescRequest, nodeDesc))
                .thenReturn(nodeDesc);
        Mockito.when(repoMock.save(nodeDesc))
                .thenReturn(nodeDescMono);

        Mono<NodeDescResponse> nodeDescResponseMono = testObj.updateNodeDesk(ID, nodeDescRequest);

        Mockito.verify(repoMock)
                .findById(ID);
        Mockito.verify(mapperMock)
                .requestToEntity(nodeDescRequest, nodeDesc);
        Mockito.when(repoMock.save(nodeDesc))
                .thenReturn(nodeDescMono);
        assertThat(nodeDescResponseMono).isNotNull();
    }

    @Test
    public void updateWithException() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeDescMono);
        Mockito.when(mapperMock.requestToEntity(nodeDescRequest, nodeDesc))
                .thenReturn(nodeDesc);
        Mockito.when(repoMock.save(nodeDesc))
                .thenReturn(nodeDescMono);

        assertThrows(NullPointerException.class, () ->
                testObj.updateNodeDesk("id12231", nodeDescRequest));
    }

    @Test
    public void deleteNodeDesk() {
        // given
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeDescMono);
        Mockito.when(repoMock.deleteById(ID))
                .thenReturn(voidMono);

        // when
        testObj.deleteNodeDesk(ID);

        Mockito.verify(repoMock)
                .findById(ID);
        Mockito.verify(repoMock)
                .deleteById(ID);
    }

    @Test
    public void deleteWithException() {
        Mockito.when(repoMock.findById(ID))
                .thenReturn(nodeDescMono);
        Mockito.when(repoMock.deleteById(ID))
                .thenReturn(voidMono);

        assertThrows(NullPointerException.class, () ->
                testObj.deleteNodeDesk("id12231"));
    }
}
