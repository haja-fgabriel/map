package ubb.scs.map.ir.seminar.studentsmanagement;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.Validator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.InMemoryRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentSerializedRepository extends InMemoryRepository<Long, Student> {
    private String filename;

    public StudentSerializedRepository(Validator<Student> validator, String filename) {
        super(validator);
        this.filename = filename;
    }

    private void readData() {
        List<Student> list;
        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(filename));
            list = (List<Student>) os.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeData() {
        List<Student> list = new ArrayList<>();
        super.findAll().forEach(student -> list.add(student));
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename));
            os.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
