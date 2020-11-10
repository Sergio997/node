package com.node.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeResponse {
    private String nodeRoot;
    private String descResponse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeResponse that = (NodeResponse) o;
        return Objects.equals(nodeRoot, that.nodeRoot) &&
                Objects.equals(descResponse, that.descResponse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeRoot, descResponse);
    }

    @Override
    public String toString() {
        return "NodeResponse{" +
                "nodeRoot=" + nodeRoot +
                ", descResponse=" + descResponse +
                '}';
    }
}
