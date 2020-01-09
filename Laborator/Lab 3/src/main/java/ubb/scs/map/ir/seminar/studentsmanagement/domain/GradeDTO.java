package ubb.scs.map.ir.seminar.studentsmanagement.domain;

import com.sun.tools.javac.util.Pair;

public class GradeDTO extends Entity<Pair<Long, Long>> {
    public float value;
    public int verifiedWeek;
    public int deadlineWeek;
    public String feedback;

    public GradeDTO(Long homeworkID, Long studentID, float value, int verifiedWeek, int deadlineWeek, String feedback) {
        this.value = value;
        this.verifiedWeek = verifiedWeek;
        this.deadlineWeek = deadlineWeek;
        this.feedback = feedback;
        setId(Pair.of(homeworkID, studentID));
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Long getHomeworkID() {
        return getId().fst;
    }

    public Long getStudentID() {
        return getId().snd;
    }

    public int getVerifiedWeek() {
        return verifiedWeek;
    }

    public void setVerifiedWeek(int verifiedWeek) {
        this.verifiedWeek = verifiedWeek;
    }

    public int getDeadlineWeek() {
        return deadlineWeek;
    }

    public void setDeadlineWeek(int deadlineWeek) {
        this.deadlineWeek = deadlineWeek;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
