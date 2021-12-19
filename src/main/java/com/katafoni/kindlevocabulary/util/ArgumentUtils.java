package com.katafoni.kindlevocabulary.util;

public class ArgumentUtils {

    public static void checkNotNull(Object object, String argumentName) {
        if (object == null) {
            throw new IllegalArgumentException(String.format("Argument %s cannot be null", argumentName));
        }
    }
}
