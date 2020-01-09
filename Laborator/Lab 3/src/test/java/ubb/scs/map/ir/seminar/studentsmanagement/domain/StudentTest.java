package ubb.scs.map.ir.seminar.studentsmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {
    private Student s;

    @BeforeEach
    void setUp() {
        s=new Student("name",2.4f, "213");
        s.setId(2l);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("name",s.getName(),"getName failed");
    }

    @Test
    void setName() {
    }

    @Test
    void getMedia() {
        assertEquals(2.4f, s.getMedia(), "getMedia failed");
    }

    @Test
    void setMedia() {
    }
}