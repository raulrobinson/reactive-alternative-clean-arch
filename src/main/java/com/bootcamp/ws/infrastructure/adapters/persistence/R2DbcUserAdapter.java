package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.UserAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.UserEntity;
import com.bootcamp.ws.domain.model.User;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.UserEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.R2dbcUserRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class R2DbcUserAdapter implements UserAdapterPort {

    private final R2dbcUserRepository reactiveRepo;
    private final UserEntityMapper mapper;

    public R2DbcUserAdapter(R2dbcUserRepository reactiveRepo, UserEntityMapper mapper) {
        this.reactiveRepo = reactiveRepo;
        this.mapper = mapper;
    }

    @Override
    public CompletableFuture<User> findById(Long id) {
        return reactiveRepo.findById(id)
                .map(mapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<List<User>> findAll() {
        return reactiveRepo.findAll()
                .map(mapper::toDomain)
                .collectList()
                .toFuture();
    }

    @Override
    public CompletableFuture<User> save(User user) {
        UserEntity entity = new UserEntity(user.getId(), user.getName());
        return reactiveRepo.save(entity)
                .map(mapper::toDomain)
                .toFuture();
    }

    @Override
    public CompletableFuture<Boolean> existsByName(String name) {
        return reactiveRepo.existsByName(name)
                .map(exists -> exists)
                .toFuture();
    }
}
