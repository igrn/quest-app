package com.javarush.quest.exception.impl;

import com.javarush.quest.exception.AppException;

import java.net.HttpURLConnection;

import static com.javarush.quest.exception.ErrorMessage.ENTITY_NOT_FOUND;
import static com.javarush.quest.exception.ErrorMessage.RESOURCE_NOT_FOUND;

public class NotFoundException extends AppException {

    public NotFoundException(String message) {
        super(message, HttpURLConnection.HTTP_NOT_FOUND, RESOURCE_NOT_FOUND);
    }

    public static NotFoundException of(Class<?> type) {
        return new NotFoundException(
                String.format(ENTITY_NOT_FOUND, type.getSimpleName())
        );
    }
}
