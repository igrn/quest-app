package com.javarush.quest.exception.impl;

import com.javarush.quest.exception.AppException;

import java.net.HttpURLConnection;

public class SecurityException extends AppException {

    public SecurityException(String message) {
        super(message, HttpURLConnection.HTTP_UNAUTHORIZED);
    }
}
