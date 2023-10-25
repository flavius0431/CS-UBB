package com.example.map_toysocialnetwork.domain.validators;

public interface Validator<T> {
    void
    validate(T entity) throws ValidationException;
}