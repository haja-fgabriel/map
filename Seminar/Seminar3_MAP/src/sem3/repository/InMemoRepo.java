package sem3.repository;

import sem3.CrudRepository;
import sem3.Entity;
import sem3.validators.ValidationException;
import sem3.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoRepo <ID, E extends Entity<ID>>implements CrudRepository<ID,E> {
    Map<ID,E> entities;
    Validator<E> validator;

    public InMemoRepo(Validator<E> validator){
        this.validator=validator;
        this.entities=new HashMap<>();
    }

    @Override
    public E findOne(ID id) {
        return entities.get(id);
    }

    @Override
    public Iterable<E> findAll() {
        entities.forEach();
    }

    @Override
    public E save(E entity) throws ValidationException {
        if(entity==null)throw new IllegalArgumentException("Entity must not be null");
        E oldValue=entities.get(entity.getId());
        if(oldValue==null){
            validator.validate(entity);
            entities.put(entity.getId(),entity);
            return null;
        }

        return entity;
    }

    @Override
    public E delete(ID id) {
        return null;
    }

    @Override
    public E update(E entity) {
        return null;
    }


}
