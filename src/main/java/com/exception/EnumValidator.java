//package com.exception;
//
//import java.util.Arrays;
//
//public class EnumValidator {
//
//    public static <E extends Enum<E>> E validateEnum(Class<E> enumClass, String value) {
//        try {
//            return Enum.valueOf(enumClass, value.toUpperCase());
//        } catch (IllegalArgumentException e) {
//            throw new InvalidEnumValueException(
//                    String.format("Invalid value '%s' for enum '%s'. Allowed values are: %s",
//                            value, enumClass.getSimpleName(),
//                            Arrays.toString(enumClass.getEnumConstants()))
//            );
//        }
//    }
//}