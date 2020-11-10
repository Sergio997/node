package com.node.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("nodeDesk")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NodeDesc extends AbstractNode {
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        NodeDesc nodeDesc = (NodeDesc) o;
        return Objects.equals(description, nodeDesc.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description);
    }

    @Override
    public String toString() {
        return "NodeRoot{" +
                "description='" + description + '\'' +
                '}';
    }
}
