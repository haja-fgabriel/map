package ubb.scs.map.ir.seminar.studentsmanagement.utils.events;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;

public class HomeworkChangeEvent implements Event {
    private ChangeEventType type;
    private Homework data, oldData;

    public HomeworkChangeEvent(ChangeEventType type, Homework data) {
        this.type = type;
        this.data = data;
    }
    public HomeworkChangeEvent(ChangeEventType type, Homework data, Homework oldData) {
        this.type = type;
        this.data = data;
        this.oldData = oldData;
    }

    public ChangeEventType getType() {
        return type;
    }

    public Homework getData() {
        return data;
    }

    public Homework getOldData() {
        return oldData;
    }
}
