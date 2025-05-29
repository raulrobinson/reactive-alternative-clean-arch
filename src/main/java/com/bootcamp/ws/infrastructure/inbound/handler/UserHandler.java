package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.spi.UserServicePort;
import com.bootcamp.ws.infrastructure.common.handler.GlobalErrorHandler;
import com.bootcamp.ws.infrastructure.inbound.dto.UserCreateDto;
import com.bootcamp.ws.infrastructure.inbound.mapper.UserMapper;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static com.bootcamp.ws.infrastructure.common.util.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
@Tag(name = "User API", description = "User API")
public class UserHandler {

    private final UserServicePort servicePort;
    private final UserMapper mapper;
    private final GlobalErrorHandler globalErrorHandler;

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return Mono.fromFuture(servicePort.listUsers())
                .flatMap(users -> ServerResponse.ok().bodyValue(users))
                .doOnError(error -> log.error(LIST_ERROR, error.getMessage()))
                .onErrorResume(globalErrorHandler::handle);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return Mono.fromFuture(servicePort.getUser(id))
                .flatMap(user -> ServerResponse.ok().bodyValue(user))
                .switchIfEmpty(ServerResponse.notFound().build())
                .doOnError(error -> log.error(FIND_RESOURCE_ERROR, error.getMessage()))
                .onErrorResume(globalErrorHandler::handle);
    }

    public Mono<ServerResponse> save(ServerRequest request) {
        return request.bodyToMono(UserCreateDto.class)
                .flatMap(u -> Mono.fromFuture(servicePort.createUser(mapper.toDomain(u))))
                .flatMap(saved -> ServerResponse.ok().bodyValue(saved))
                .doOnError(error -> log.error(CREATE_ERROR, error.getMessage()))
                .onErrorResume(globalErrorHandler::handle);
    }
}
