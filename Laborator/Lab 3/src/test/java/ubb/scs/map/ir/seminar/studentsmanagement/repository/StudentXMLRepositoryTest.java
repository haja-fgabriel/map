package ubb.scs.map.ir.seminar.studentsmanagement.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.StudentXMLRepository;

class StudentXMLRepositoryTest {

    private StudentXMLRepository repo;

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
        repo = new StudentXMLRepository(new StudentValidator(), "test.xml");
        populate();
    }

    @AfterEach
    void tearDown() {
//        repo.saveData();
//        try {
//            Files.delete(Path.of("test.xml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
        repo.findAll().forEach(System.out::println);
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

    @Test
    void loadData() {
        //repo.findAll().forEach(System.out::println);
    }

    @Test
    void saveData() {
    }

}