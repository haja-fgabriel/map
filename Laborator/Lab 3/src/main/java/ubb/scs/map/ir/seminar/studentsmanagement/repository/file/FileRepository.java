package ubb.scs.map.ir.seminar.studentsmanagement.repository.file;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Entity;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.CrudRepository;

public interface FileRepository<ID, E extends Entity<ID>> extends CrudRepository<ID, E> {
    void loadData();
    void saveData();
    E parse(Object line);
    Object unparse(E entity);

    @Override
    E save(E entity);

    @Override
    E delete(ID id);

    @Override
    E update(E entity);
}
