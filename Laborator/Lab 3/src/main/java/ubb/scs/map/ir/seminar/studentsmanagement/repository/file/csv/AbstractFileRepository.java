package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Entity;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.InMemoryRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractFileRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> implements FileRepository<ID, E> {
    private String fileName;

    @Override
    public E parse(Object line) {
        return null;
    }

    @Override
    public Object unparse(E entity) {
        return null;
    }

    public AbstractFileRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
    }
    public void loadData() {
        Path path = Paths.get(fileName);
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(linie -> {
                super.save(parse(linie));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            PrintStream output = new PrintStream(fileName);
            super.findAll().forEach(x -> {
                output.println(unparse(x));
            });
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E save(E entity) {
        E e = super.save(entity);
        saveData();
        return e;
    }

    @Override
    public E delete(ID id) {
        E e = super.delete(id);
        saveData();
        return e;
    }

    @Override
    public E update(E entity) {
        E e = super.update(entity);
        saveData();
        return e;
    }
}
