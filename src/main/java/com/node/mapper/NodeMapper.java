package com.node.mapper;

import com.node.dto.request.AbstractNodeRequest;
import com.node.dto.response.NodeResponse;
import com.node.model.Node;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeMapper {

    NodeResponse modelToResponse(Node node);

    Node requestToModel(AbstractNodeRequest dto);

    Node requestToEntity(AbstractNodeRequest dto, @MappingTarget Node node);

}
