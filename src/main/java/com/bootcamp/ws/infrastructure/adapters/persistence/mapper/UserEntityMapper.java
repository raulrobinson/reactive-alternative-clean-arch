package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.model.User;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setName(user.getName());
        return userEntity;
    }

    public User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;
        return new User.Builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }
}
