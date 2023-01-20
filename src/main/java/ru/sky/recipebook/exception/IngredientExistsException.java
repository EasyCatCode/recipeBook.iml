package ru.sky.recipebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientExistsException extends RuntimeException{

    public IngredientExistsException() {
        super("Ingredients Book already contains this ingredient.");
    }

    public IngredientExistsException(String message) {
        super(message);
    }

    public IngredientExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public IngredientExistsException(Throwable cause) {
        super(cause);
    }

    protected IngredientExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
