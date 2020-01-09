package ubb.scs.map.ir.seminar.studentsmanagement.domain;

public class Professor extends Entity<Long> {
    private String surname;
    private String name;
    private String mail;

    public Professor(String surname, String name, String mail) {
        this.surname = surname;
        this.name = name;
        this.mail = mail;
    }

    // TODO Gradle tests

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
