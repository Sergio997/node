package com.node.mapper;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.model.NodeDesc;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.MockitoAnnotations;

public class NodeDescMapperTest {
    private final NodeDescMapper testObject = Mappers.getMapper(NodeDescMapper.class);

    private static final String ID = "Id";
    private static final String NAME = "Title";
    private static final String DESCRIPTION = "Text";

    private NodeDesc nodeDesc;
    private NodeDescResponse nodeDescResponse;
    private NodeDescRequest nodeDescRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        nodeDesc = new NodeDesc() {{
            setId(ID);
            setName(NAME);
            setDescription(DESCRIPTION);
        }};

        nodeDescResponse = new NodeDescResponse() {{
            setName(NAME);
            setDescription(DESCRIPTION);
        }};

        nodeDescRequest = new NodeDescRequest() {{
            setName(NAME);
            setDescription(DESCRIPTION);
        }};
    }

    @Test
    public void modelToResponseTest() {
        NodeDescResponse nodeDescResponse = testObject.modelToResponse(nodeDesc);

        Assertions.assertNotNull(nodeDescResponse);
        Assertions.assertEquals(nodeDescResponse.getDescription(), nodeDesc.getDescription());
        Assertions.assertEquals(nodeDescResponse.getName(), nodeDesc.getName());
    }

    @Test
    public void requestToModelTest() {
        NodeDesc nodeDesc = testObject.requestToModel(nodeDescRequest);

        Assertions.assertNotNull(nodeDescResponse);
        Assertions.assertEquals(nodeDescResponse.getDescription(), nodeDesc.getDescription());
        Assertions.assertEquals(nodeDescResponse.getName(), nodeDesc.getName());
    }

    @Test
    public void requestToEntityTest() {
        nodeDescRequest.setDescription("title2");
        NodeDesc nodeDesc = testObject.requestToEntity(nodeDescRequest, this.nodeDesc);

        Assertions.assertNotNull(nodeDescResponse);
        Assertions.assertEquals(nodeDescResponse.getName(), this.nodeDesc.getName());
    }
}
