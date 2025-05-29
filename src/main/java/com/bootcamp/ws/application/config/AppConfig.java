package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.UserAdapterPort;
import com.bootcamp.ws.domain.service.UserService;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.UserEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.R2dbcUserRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.R2DbcUserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    @Bean
    public UserService createTechnologyUseCase(UserAdapterPort adapterPort) {
        return new UserService(adapterPort);
    }

    @Bean
    public R2DbcUserAdapter r2DbcUserAdapter(R2dbcUserRepository reactiveRepo,
                                             UserEntityMapper mapper) {
        return new R2DbcUserAdapter(reactiveRepo, mapper);
    }
}
