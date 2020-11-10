package com.node.mapper;

import com.node.dto.request.NodeRootRequest;
import com.node.dto.response.NodeRootResponse;
import com.node.model.NodeRoot;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeRootMapper {

    NodeRootResponse modelToResponse(NodeRoot nodeRoot);

    NodeRoot requestToModel(NodeRootRequest dto);

    NodeRoot requestToEntity(NodeRootRequest dto, @MappingTarget NodeRoot nodeRoot);

}
