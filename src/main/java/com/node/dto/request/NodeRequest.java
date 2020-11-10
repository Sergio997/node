package com.node.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeRequest {

    private String nodeDesc;
    private String nodeRoot;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeRequest that = (NodeRequest) o;
        return Objects.equals(nodeDesc, that.nodeDesc) &&
                Objects.equals(nodeRoot, that.nodeRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeDesc, nodeRoot);
    }

    @Override
    public String toString() {
        return "NodeRequest{" +
                "nodeDesc=" + nodeDesc +
                ", nodeRoot=" + nodeRoot +
                '}';
    }
}
