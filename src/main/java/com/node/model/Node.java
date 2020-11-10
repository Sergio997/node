package com.node.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("node")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Node {

    @Id
    private String id;
    private String nodeDesc;
    private String nodeRoot;

    public Node(String nodeDesc, String nodeRoot) {
        this.nodeDesc = nodeDesc;
        this.nodeRoot = nodeRoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id) &&
                Objects.equals(nodeDesc, node.nodeDesc) &&
                Objects.equals(nodeRoot, node.nodeRoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodeDesc, nodeRoot);
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", nodeDesc=" + nodeDesc +
                ", nodeRoot=" + nodeRoot +
                '}';
    }
}
