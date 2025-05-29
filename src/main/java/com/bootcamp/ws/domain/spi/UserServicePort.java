package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UserServicePort {
    CompletableFuture<User> getUser(Long id);
    CompletableFuture<List<User>> listUsers();
    CompletableFuture<User> createUser(User user);
}
