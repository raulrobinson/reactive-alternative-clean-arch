package com.bootcamp.ws.domain.service;

import com.bootcamp.ws.domain.api.UserAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.User;
import com.bootcamp.ws.domain.spi.UserServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class UserService implements UserServicePort {

    private final UserAdapterPort adapterPort;

    public UserService(UserAdapterPort adapterPort) {
        this.adapterPort = adapterPort;
    }

    @Override
    public CompletableFuture<User> getUser(Long id) {
        return adapterPort.findById(id);
    }

    @Override
    public CompletableFuture<List<User>> listUsers() {
        return adapterPort.findAll();
    }

    @Override
    public CompletableFuture<User> createUser(User request) {
        return adapterPort.existsByName(request.getName())
                .thenCompose(exists -> {
                    if (exists) {
                        return CompletableFuture.failedFuture(new DuplicateResourceException(
                                TechnicalMessage.ALREADY_EXISTS,
                                "TECH_DUPLICATE",
                                request.getName()
                        ));
                    }
                    return adapterPort.save(request);
                });
    }
}
