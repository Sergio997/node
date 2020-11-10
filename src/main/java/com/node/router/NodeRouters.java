package com.node.router;

import com.node.handler.NodeHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@AllArgsConstructor
public class NodeRouters {

    @Bean
    public RouterFunction<ServerResponse> nodeRouter(NodeHandler nodeHandler) {
        return RouterFunctions
                .route(GET("functional/nodes").and(accept(MediaType.APPLICATION_JSON))
                        , nodeHandler::getAllNode)
                .andRoute(POST("functional/node").and(accept(MediaType.APPLICATION_JSON))
                        , nodeHandler::createNode);
    }
}
