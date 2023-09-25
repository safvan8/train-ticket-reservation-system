package com.safvan.util;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * Utility class for common operations.
 */
public class CommonUtils {
    /**
     * Predicate to check if an object is not null.
     */
    public static final Predicate<Object> isNotNull = Objects::nonNull;

    /**
     * Predicate to check if an object is null.
     */
    public static final Predicate<Object> isNull = Objects::isNull;
}
