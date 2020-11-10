package com.node.mapper;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.model.NodeRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;

public class NodeRootMapperTest {
    private final NodeRootMapper testObject = Mappers.getMapper(NodeRootMapper.class);

    private static final String ID = "Id";
    private static final String NAME = "Title";

    private NodeRoot nodeRoot;
    private NodeRootResponse nodeRootResponse;
    private NodeRootRequest nodeRootRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeRoot = new NodeRoot() {{
            setId(ID);
            setName(NAME);
        }};

        nodeRootResponse = new NodeRootResponse() {{
            setName(NAME);
        }};

        nodeRootRequest = new NodeRootRequest() {{
            setName(NAME);
        }};
    }

    @Test
    public void modelToResponseTest() {
        NodeRootResponse nodeRootResponse = testObject.modelToResponse(nodeRoot);

        Assertions.assertNotNull(nodeRootResponse);
        Assertions.assertEquals(nodeRootResponse.getName(), nodeRoot.getName());
    }

    @Test
    public void requestToModelTest() {
        NodeRoot nodeRoot = testObject.requestToModel(nodeRootRequest);

        Assertions.assertNotNull(nodeRoot);
        Assertions.assertEquals(nodeRoot.getName(), this.nodeRoot.getName());
    }

    @Test
    public void requestToEntityTest() {
        nodeRootRequest.setName("title2");
        NodeRoot nodeRoot = testObject.requestToEntity(nodeRootRequest, this.nodeRoot);

        Assertions.assertNotNull(nodeRoot);
    }
}
