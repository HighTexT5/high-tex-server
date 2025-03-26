package com.uet.hightex.utils;

import java.lang.reflect.Field;

public class MapperUtils {
    public static <S, D> void map(S source, D destination) {
        if (source == null || destination == null) {
            throw new IllegalArgumentException("Source and destination objects must not be null");
        }

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] destinationFields = destination.getClass().getDeclaredFields();

        for (Field sourceField : sourceFields) {
            sourceField.setAccessible(true);
            for (Field destinationField : destinationFields) {
                destinationField.setAccessible(true);
                if (sourceField.getName().equals(destinationField.getName()) &&
                        sourceField.getType().equals(destinationField.getType())) {
                    try {
                        destinationField.set(destination, sourceField.get(source));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to map field: " + sourceField.getName(), e);
                    }
                }
            }
        }
    }
}
