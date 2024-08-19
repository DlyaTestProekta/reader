package ru.pachan.reader.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    OBJECT_NOT_FOUND("Объект не найден");

    private final String message;
}
