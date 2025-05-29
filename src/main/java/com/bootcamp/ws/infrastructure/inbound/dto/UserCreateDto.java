package com.bootcamp.ws.infrastructure.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User create DTO")
public class UserCreateDto {
    @Schema(description = "User Name", example = "John Doe")
    private String name;
}
