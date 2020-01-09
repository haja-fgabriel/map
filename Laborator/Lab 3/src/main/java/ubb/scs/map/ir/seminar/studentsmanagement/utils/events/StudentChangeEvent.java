package ubb.scs.map.ir.seminar.studentsmanagement.utils.events;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;

public class StudentChangeEvent implements Event {
    private ChangeEventType type;
    private Student data, oldData;

    public StudentChangeEvent(ChangeEventType type, Student data) {
        this.type = type;
        this.data = data;
    }
    public StudentChangeEvent(ChangeEventType type, Student data, Student oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Student getData() {
        return data;
    }

    public Student getOldData() {
        return oldData;
    }
}
