package com.katafoni.kindlevocabulary.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentUtils {
    private static final Logger logger = LoggerFactory.getLogger(ArgumentUtils.class);

    public static void checkNotNull(Object object, String argumentName) {
        if (object == null) {
            logger.error(String.format("Argument %s cannot be null", argumentName));
            throw new IllegalArgumentException(String.format("Argument %s cannot be null", argumentName));
        }
    }
}
