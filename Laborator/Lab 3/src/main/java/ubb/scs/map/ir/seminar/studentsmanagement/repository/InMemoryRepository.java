package ubb.scs.map.ir.seminar.studentsmanagement.repository;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Entity;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<ID, E extends Entity<ID>> implements CrudRepository<ID,E> {
    private Map<ID, E> entities;
    private Validator<E> validator;

    public InMemoryRepository(Validator<E> validator) {
        this.validator = validator;
        entities = new HashMap<ID, E>();
    }

    @Override
    public E findOne(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must not be null");
        return entities.get(id);
    }


    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public E save(E entity) {
        if(entity == null)
            throw new IllegalArgumentException("entity must be not null");
        if(entities.containsKey(entity.getId()))
            return entity;
        validator.validate(entity);
        entities.put(entity.getId(), entity);
        return null;
    }

    @Override
    public E delete(ID id) {
        if (id == null)
            throw new IllegalArgumentException("id must not be null");
        return entities.remove(id);
    }

    @Override
    public E update(E entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must not be null");
        validator.validate(entity);
        if (entities.replace(entity.getId(), entity) == null)
            return entity;
        return null;
    }
}
