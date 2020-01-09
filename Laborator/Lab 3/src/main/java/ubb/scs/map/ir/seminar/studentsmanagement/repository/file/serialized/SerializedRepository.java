package ubb.scs.map.ir.seminar.studentsmanagement.repository.file.serialized;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Entity;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.InMemoryRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedRepository<ID, E extends Entity<ID>> extends InMemoryRepository<ID, E> implements FileRepository<ID, E> {

    private String fileName;

    public SerializedRepository(Validator<E> validator, String fileName) {
        super(validator);
        this.fileName = fileName;
    }

    @Override
    public void saveData(){
        List<E> list=new ArrayList<>();
        super.findAll().forEach(entity->list.add(entity));
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName))) {
            os.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void loadData() {
        List<E> list = new ArrayList<>();
        try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName))){
            list = (List<E>) is.readObject();
            list.forEach(x->super.save(x));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E save(E entity) {
        E local = super.save(entity);
        if(local == null)
            saveData();
        return local;
    }

    @Override
    public E parse(Object line) {
        return null;
    }

    @Override
    public Object unparse(E entity) {
        return null;
    }
}
