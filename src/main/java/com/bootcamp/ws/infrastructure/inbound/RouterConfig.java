package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.domain.model.User;
import com.bootcamp.ws.infrastructure.common.handler.ErrorDto;
import com.bootcamp.ws.infrastructure.inbound.dto.UserCreateDto;
import com.bootcamp.ws.infrastructure.inbound.handler.UserHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class RouterConfig {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/users",
                    produces = "application/json",
                    method = RequestMethod.GET,
                    beanClass = UserHandler.class,
                    beanMethod = "getAll",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "getAllUsers",
                            summary = "Get all users",
                            description = "Fetches all users from the database",
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "200",
                                            description = "OK",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = User.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "204", description = "No Content"
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "400", description = "Bad Request",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDto.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500", description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDto.class)
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users/{id}",
                    produces = "application/json",
                    method = RequestMethod.GET,
                    beanClass = UserHandler.class,
                    beanMethod = "getById",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "getUserById",
                            summary = "Get user by ID",
                            description = "Fetches a user by their ID from the database",
                            parameters = {
                                    @Parameter(name = "id", in = ParameterIn.PATH, description = "User ID", example = "1"),
                            }
                    )
            ),
            @RouterOperation(
                    path = "/users",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = UserHandler.class,
                    beanMethod = "save",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "createUser",
                            summary = "Create a new user",
                            description = "Creates a new user in the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "User Create Request",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = UserCreateDto.class)
                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return RouterFunctions.route()
                .GET("/users", handler::getAll)
                .GET("/users/{id}", handler::getById)
                .POST("/users", handler::save)
                .build();
    }
}