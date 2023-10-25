package com.example.map_toysocialnetwork.domain.validators;

import com.example.map_toysocialnetwork.domain.Utilizator;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        //TODO: implement method validate
        String errors= new String();
        if(entity.getLastName().isEmpty()){
            errors += " FirstName is empty ";
        }
        if(entity.getLastName().isEmpty()){
            errors += " LastName is empty ";
        }
        if(entity.getId()<0 ){

            errors +=" id is negative ";
        }
        if(errors.length()>0)
            throw new ValidationException(errors);
    }
}
