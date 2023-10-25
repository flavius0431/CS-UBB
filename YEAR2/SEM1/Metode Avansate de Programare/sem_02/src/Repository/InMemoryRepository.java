package Repository;

import Domain.Validator.Validator;

import java.util.Map;
import Domain.Entity;

public abstract class InMemoryRepository <ID, E extends Entity<ID>> implements Repository<E, ID>{

    private Map<ID,E> entities;
    private Validator<E> validator;

    public InMemoryRepository(Validator validator) {
    }


    @Override
    public E save(E entity) {
        if(entity==null){
        }
        return null;
    }

    @Override
    public E delete(ID id) {
        return null;
    }


    @Override
    public E findOne(ID id) {
        return null;
    }

    @Override
    public Iterable<E> findAll() {
        return null;
    }
}