package ubb.scs.map.ir.seminar.studentsmanagement.utils.observer;

import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.Event;

public interface Observable<E extends Event> {
    void addObserver(Observer<E> e);
    void removeObserver(Observer<E> e);
    void notifyObservers(E t);
}
