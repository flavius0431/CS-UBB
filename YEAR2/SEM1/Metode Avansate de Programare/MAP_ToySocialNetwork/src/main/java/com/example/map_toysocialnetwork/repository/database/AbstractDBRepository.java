package com.example.map_toysocialnetwork.repository.database;

import com.example.map_toysocialnetwork.domain.Entity;
import com.example.map_toysocialnetwork.domain.validators.Validator;
import com.example.map_toysocialnetwork.repository.Repository0;

public abstract class AbstractDBRepository <ID,E extends Entity<ID>> implements Repository0<ID, E> {

    protected String url;
    protected String username;
    protected String password;
    protected Validator<E> validator;
    protected String table;

    public AbstractDBRepository(String url, String username, String password, Validator<E> validator) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.validator = validator;

    }

    @Override
    public abstract E findOne(ID id) ;

    @Override
    public abstract Iterable<E> findAll() ;

    @Override
    public abstract E save(E entity) ;

    @Override
    public abstract E delete(ID id) ;

    @Override
    public abstract E update(E entity);
}
