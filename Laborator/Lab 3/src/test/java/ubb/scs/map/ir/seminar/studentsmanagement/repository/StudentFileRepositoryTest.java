package ubb.scs.map.ir.seminar.studentsmanagement.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.StudentFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

class StudentFileRepositoryTest {
    private StudentFileRepository repo;


    void populate() {
        Student s = new Student("Mihoc", 9.8f, "224");
        s.setId(1l);
        Student s2 = new Student("Manole", 7.8f, "224");
        s2.setId(2l);
        Student s3 = new Student("Denisa", 9.4f, "214");
        s3.setId(1l);
        assert(repo.save(s) == null);
        assert(repo.save(s3) != null);
        assert(repo.save(s2) == null);
        repo.saveData();
    }

    @BeforeEach
    void setUp() {
        StudentValidator valid = new StudentValidator();
        repo = new StudentFileRepository(valid, "test.txt");
        populate();
    }

    @AfterEach
    void tearDown() {
        try {
            Files.delete(Path.of("test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findOne() {


        System.out.println("[TEST] Entities are saved");

        Student s4 = repo.findOne(2l);
        assert(s4 != null);
        assert(s4.getId() == 2);
    }

    @Test
    void findAll() {
        AtomicInteger count = new AtomicInteger();
        repo.findAll().forEach(student -> {
            count.getAndIncrement(); assert(student.getId() > 0);});
        assert(count.get() > 0);
    }


    @Test
    void loadData() {
        repo.loadData();
    }

    @Test
    void delete() {
        repo.delete(1l);
        assert(repo.findOne(1l) == null);
    }

    @Test
    void update() {
        Student s = new Student("Darius", 10f, "111");
        s.setId(1l);
        assert(repo.update(s) == null);
    }
}