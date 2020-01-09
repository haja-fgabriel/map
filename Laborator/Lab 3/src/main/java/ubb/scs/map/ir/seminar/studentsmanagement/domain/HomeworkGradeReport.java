package ubb.scs.map.ir.seminar.studentsmanagement.domain;

public class HomeworkGradeReport {
    private Long homeworkID;
    private String description;
    private float average;

    public HomeworkGradeReport(Long homeworkID) {
        this.homeworkID = homeworkID;
        this.description = "";
        this.average = 0;
    }

    public Long getHomeworkID() {
        return homeworkID;
    }

    public void setHomeworkID(Long homeworkID) {
        this.homeworkID = homeworkID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "HomeworkGradeReport{" +
                "homeworkID=" + homeworkID +
                ", description='" + description + '\'' +
                ", average=" + average +
                '}';
    }
}
