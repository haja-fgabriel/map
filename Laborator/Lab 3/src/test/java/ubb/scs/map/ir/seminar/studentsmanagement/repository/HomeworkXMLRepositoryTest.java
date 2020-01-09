package ubb.scs.map.ir.seminar.studentsmanagement.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.HomeworkValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.HomeworkXMLRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

class HomeworkXMLRepositoryTest {
    private HomeworkXMLRepository repo;

    void populate() {
        Homework h1 = new Homework("Corect", 1, 14);
        h1.setId(1L);
        Homework h2 = new Homework("Incorect", 14, 14);
        h2.setId(1L);
        Homework h3 = new Homework("Corect!!!", 13, 14);
        h3.setId(2L);
        assert(repo.save(h1) == null);
        assert(repo.save(h2) != null);
        assert(repo.save(h3) == null);
        repo.saveData();
    }

    @BeforeEach
    void setUp() {
        //System.out.println("Current path :" + Paths.get("test.txt").toAbsolutePath());
        repo = new HomeworkXMLRepository(new HomeworkValidator(), "test.xml");
        populate();
    }

    @AfterEach
    void tearDown() {
        try {
            Files.delete(Paths.get("test.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    void loadData() {
    }

    @Test
    void saveData() {
    }

    @Test
    void findOne() {
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

    @Test
    void parse() {
    }

    @Test
    void unparse() {
    }
}