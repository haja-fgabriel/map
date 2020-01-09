package ubb.scs.map.ir.seminar.studentsmanagement.services.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.StudentValidator;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.xml.StudentXMLRepository;

import java.nio.file.Files;
import java.nio.file.Path;

class StudentServiceTest {

    private StudentXMLRepository repository;
    private StudentService service;


    void populate() {
        assert(service.addStudent(1l, "Mihoc", 9.8f, "224") == null);
        assert(service.addStudent(2l, "Manole", 7.8f, "224") == null);
        assert(service.addStudent(2l, "Denisa", 9.4f, "214") != null);
    }

    @BeforeEach
    void setUp() {
        repository = new StudentXMLRepository(new StudentValidator(), "test.xml");
        service = new StudentService(repository);
        if (!Files.exists(Path.of("test.xml")))
            populate();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void getStudentsByGroup() {
        service.getStudentsByGroup("224")
                .forEach(student -> {
                    System.out.println("we have " + student.toString());
                    assert(student.getGroup().equals("224"));} );
    }
}