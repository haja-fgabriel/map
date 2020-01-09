package ubb.scs.map.ir.seminar.studentsmanagement.domain;


import com.sun.tools.javac.util.Pair;

import java.time.LocalDateTime;

public class Grade extends Entity<Pair<Long, Long>> {
    private LocalDateTime date;
    private String professor;
    private float number;
    private int delay;
    private int timeOff;
    private String feedback;

    public Grade(Long homeworkID, Long studentID, LocalDateTime date, float number) {
        this.date = date;
        super.setId(new Pair<>(homeworkID, studentID));
        this.number = number;
        this.feedback = "";
    }

    public Grade(Long homeworkID, Long studentID, LocalDateTime date, float number, String feedback, String professor) {
        this.date = date;
        super.setId(new Pair<>(homeworkID, studentID));
        this.number = number;
        this.feedback = feedback;
        this.professor = professor;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getHomeworkID() {
        return super.getId().fst;
    }

    public Long getStudentID() {
        return super.getId().snd;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "homework ID=" + getHomeworkID() +
                ", student ID=" + getStudentID() +
                "date=" + date +
                ", number=" + number +
                ", delay=" + delay +
                '}';
    }
}
