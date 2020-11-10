package com.node.mapper;

import com.node.dto.request.NodeDescRequest;
import com.node.dto.response.NodeDescResponse;
import com.node.model.NodeDesc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeDescMapper {

    NodeDescResponse modelToResponse(NodeDesc nodeDesc);

    NodeDesc requestToModel(NodeDescRequest dto);

    NodeDesc requestToEntity(NodeDescRequest dto, @MappingTarget NodeDesc nodeRoot);

}
