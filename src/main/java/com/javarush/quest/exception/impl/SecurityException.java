package com.javarush.quest.exception.impl;

import com.javarush.quest.exception.AppException;

import java.net.HttpURLConnection;

import static com.javarush.quest.exception.ErrorMessage.AUTHENTICATION_REQUIRED;

public class SecurityException extends AppException {

    public SecurityException(String message) {
        super(message, HttpURLConnection.HTTP_UNAUTHORIZED, AUTHENTICATION_REQUIRED);
    }
}
