package com.example.map_toysocialnetwork.domain.validators;

import com.example.map_toysocialnetwork.domain.Prietenie;

public class PrietenieValidator implements Validator<Prietenie> {
    @Override
    public void validate(Prietenie entity) throws ValidationException {
        String errors = new String();
        if (entity.getID1().describeConstable().isEmpty()) {
            errors += "First ID is empty ";
        }
        if (entity.getID2().describeConstable().isEmpty()) {
            errors += "Second ID is empty ";
        }
        if (errors.length() > 0) {
            throw new ValidationException(errors);
        }
    }
}