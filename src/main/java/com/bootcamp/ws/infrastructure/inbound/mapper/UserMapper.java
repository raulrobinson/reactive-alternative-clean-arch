package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.User;
import com.bootcamp.ws.infrastructure.inbound.dto.UserCreateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserCreateDto u) {
        if (u == null) return null;
        return new User.Builder()
                .name(u.getName())
                .build();
    }
}
