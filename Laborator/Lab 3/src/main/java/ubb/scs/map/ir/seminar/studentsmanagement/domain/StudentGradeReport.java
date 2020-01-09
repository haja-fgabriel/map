package ubb.scs.map.ir.seminar.studentsmanagement.domain;

public class StudentGradeReport {
    private Long studentID;
    private String name;
    private float average;

    public StudentGradeReport(Long studentID) {
        this.studentID = studentID;
        name = "";
        average = 0;
    }

    public StudentGradeReport(Long studentID, String name, float average) {
        this.studentID = studentID;
        this.name = name;
        this.average = average;
    }

    public Long getStudentID() {
        return studentID;
    }

    public void setStudentID(Long studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "GradeReport{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", average=" + average +
                '}';
    }
}
