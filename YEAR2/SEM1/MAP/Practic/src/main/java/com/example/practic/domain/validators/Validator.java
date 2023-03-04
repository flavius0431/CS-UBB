package com.example.practic.domain.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
