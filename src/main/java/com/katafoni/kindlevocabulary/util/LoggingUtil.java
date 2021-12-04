package com.katafoni.kindlevocabulary.util;

public class LoggingUtil {

    public static String getLoggingMessage(String errorCode, String message) {
        return errorCode + " : " + message;
    }
}
