package com.exception;

import com.constant.Frequency;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.constant.Status;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler(InvalidFormatException.class)
//    public ResponseEntity<Object> invalidArgumentsException(InvalidFormatException e){
//       String fieldName=e.getPath().get(0).getFieldName();
//        return new ResponseEntity<>(String.format("invalid value of fields '%s'.allowed values :'%s'",
//                fieldName, Arrays.toString(Frequency.values())),HttpStatus.BAD_REQUEST);
//    }

    // List of enums to handle
    private static final List<Class<? extends Enum<?>>> enumClasses = Arrays.asList(Frequency.class,
            Status.class);

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException e) {
        String fieldName = e.getPath().get(0).getFieldName();
        for (Class<? extends Enum<?>> enumClass : enumClasses) {
            if (fieldBelongsToEnum(fieldName, enumClass)) {
                Enum<?>[] enumValues = enumClass.getEnumConstants();
                return new ResponseEntity<>(String.format("Invalid value for field '%s'.Allowed values:'%s'",
                        fieldName, Arrays.toString(enumValues)),
                        HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(String.format("Invalid value for field '%s'.", fieldName), HttpStatus.BAD_REQUEST);
    }

    private boolean fieldBelongsToEnum(String fieldName, Class<? extends Enum<?>> enumClass) {
        return Arrays.stream(enumClass.getDeclaredFields())
                .anyMatch(field -> field.getName().equalsIgnoreCase(fieldName));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> IllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);

    }

}
