package sem3;

import java.util.Objects;

public class Student extends Entity<Long> implements Comparable<Student>{
    private String name;
    private float media;
    public Student(){ }

    public Student(String name, float media){
        this.name=name;
        this.media=media;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long aLong) {
        super.setId(aLong);
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", media=" + media +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.media, media) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, media);
    }

    @Override
    public int compareTo(Student o) {
        return (int)(this.getMedia()-o.getMedia());
    }
}
