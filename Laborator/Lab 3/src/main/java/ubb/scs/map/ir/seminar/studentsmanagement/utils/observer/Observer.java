package ubb.scs.map.ir.seminar.studentsmanagement.utils.observer;


import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.Event;

public interface Observer<E extends Event> {
    void update(E e);
}
