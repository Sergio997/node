package com.node.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("nodeRoot")
@Getter
@Setter
@NoArgsConstructor
public class NodeRoot extends AbstractNode{

}
