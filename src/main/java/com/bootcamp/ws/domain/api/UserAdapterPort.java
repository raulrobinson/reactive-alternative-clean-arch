package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserAdapterPort {
    CompletableFuture<User> findById(Long id);
    CompletableFuture<List<User>> findAll();
    CompletableFuture<User> save(User user);

    CompletableFuture<Boolean> existsByName(String name);
}
