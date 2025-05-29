package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface R2dbcUserRepository extends ReactiveCrudRepository<UserEntity, Long> {
    Mono<Boolean> existsByName(String name);
    //Mono<UserEntity> findById(String id);
}

