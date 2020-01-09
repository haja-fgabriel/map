package ubb.scs.map.ir.seminar.studentsmanagement.repository;

import com.sun.tools.javac.util.Pair;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Grade;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.GradeValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.csv.GradeFileRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

class GradeFileRepositoryTest {

    private GradeFileRepository repo;

    void populate() {
        assert(repo.save(new Grade(1l, 1l, LocalDateTime.now(), 10f)) == null);
        assert(repo.save(new Grade(2l, 1l, LocalDateTime.now(), 10f)) == null);
        assert(repo.save(new Grade(1l, 2l, LocalDateTime.now(), 10f)) == null);


        repo.saveData();
    }

    @BeforeEach
    void setUp() {
        repo = new GradeFileRepository(new GradeValidator(), "test.txt");
        populate();
    }

    @AfterEach
    void tearDown() {
        try {
            Files.delete(Paths.get("test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void loadData() {
        repo.loadData();
    }

    @Test
    void saveData() {
        repo.saveData();
    }

    @Test
    void findOne() {
        Grade grade = repo.findOne(Pair.of(1l, 1l));
        assert(grade != null);
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}