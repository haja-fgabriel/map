package ubb.scs.map.ir.seminar.studentsmanagement.domain;


import java.util.Objects;

public class Student extends Entity<Long> implements Comparable<Student> {
    private String name;
    private float media;
    private int timeOff;
    private String group;
    // TODO Gradle tests

    public Student() {
    }

    public Student(String name, float media, String group) {
        this.name = name;
        this.media = media;
        this.timeOff = 0;
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getTimeOff() {
        return timeOff;
    }

    public void setTimeOff(int timeOff) {
        this.timeOff = timeOff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id="+ getId() + " " +
                "name='" + name + '\'' +
                ", media=" + media +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Float.compare(student.getMedia(), getMedia()) == 0 &&
                getName().equals(student.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMedia());
    }


    @Override
    public int compareTo(Student o) {
        return (int) (o.getMedia() - this.getMedia());
    }
}
