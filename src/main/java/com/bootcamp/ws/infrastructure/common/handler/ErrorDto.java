package com.bootcamp.ws.infrastructure.common.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error DTO", title = "Error DTO")
public class ErrorDto {

    @Schema(description = "Error code", example = "Code of the error")
    private String code;

    @Schema(description = "Error message", example = "Error message")
    private String message;

    @Schema(description = "Parameter that caused the error", example = "Parameter that caused the error")
    private String parameter;

    @Schema(description = "Date of the error occurrence", example = "2023-10-01T12:00:00Z")
    private String date;

    public static ErrorDto of(String code, String message, String parameter, String date) {
        return new ErrorDto(code, message, parameter, date);
    }
}
