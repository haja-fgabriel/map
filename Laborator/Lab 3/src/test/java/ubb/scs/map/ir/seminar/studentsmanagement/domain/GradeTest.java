package ubb.scs.map.ir.seminar.studentsmanagement.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    private Grade g;

    @BeforeEach
    void setUp() {
        g = new Grade(11l, 1l, LocalDateTime.now(), 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetDate() {
    }

    @Test
    void testSetDate() {
    }

    @Test
    void testGetProfessorID() {
    }

    @Test
    void testSetProfessorID() {
    }

    @Test
    void testGetStudentID() {
    }

    @Test
    void testSetStudentID() {
    }

    @Test
    void testGetNumber() {
        assert(g.getNumber() >= 1 && g.getNumber() <= 10);
    }

    @Test
    void testSetNumber() {
        g.setNumber(1);
        g.setNumber(-99999);
        g.setNumber(1000);
        g.setNumber(10);
    }
}