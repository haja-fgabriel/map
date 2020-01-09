package ubb.scs.map.ir.seminar.studentsmanagement.domain;

import ubb.scs.map.ir.seminar.studentsmanagement.utils.Weeks;

import java.text.SimpleDateFormat;
import java.util.Date;

import static ubb.scs.map.ir.seminar.studentsmanagement.utils.Calendar.*;

public class Homework extends Entity<Long> implements Comparable<Homework> {
    private String description;
    private int startWeek;
    private int deadlineWeek;


    public Homework(String description, int startWeek, int deadlineWeek) {
        this.description = description;
        this.startWeek = startWeek;
        this.deadlineWeek = deadlineWeek;
    }

    public Homework(String description, int deadlineWeek) {
        this.description = description;
        this.startWeek = Weeks.getCurrentWeek();
        this.deadlineWeek = deadlineWeek;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "description='" + description + '\'' +
                "startWeek=" + startWeek +
                ", deadlineWeek=" + deadlineWeek +
                '}';
    }

    @Override
    public int compareTo(Homework homework) {
        return (int) (homework.getId() - this.getId());
    }
}
