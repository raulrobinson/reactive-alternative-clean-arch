package com.bootcamp.ws.domain.exception;

import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;

public class InvalidValueException extends BusinessException {
    public InvalidValueException(TechnicalMessage parameter, String message) {
        super(message, "INVALID_VALUE", parameter.getMessage());
    }
}
