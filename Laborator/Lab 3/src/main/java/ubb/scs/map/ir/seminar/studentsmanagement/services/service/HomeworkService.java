package ubb.scs.map.ir.seminar.studentsmanagement.services.service;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Homework;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.ValidationException;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.CrudRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.InMemoryRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.Weeks;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.ChangeEventType;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.HomeworkChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observable;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

// TODO - add observable to HomeworkService and connect it to HomeworkController

public class HomeworkService implements Observable<HomeworkChangeEvent> {
    private List<Observer<HomeworkChangeEvent>> observers;

    private FileRepository<Long, Homework> repository;

    public HomeworkService(FileRepository<Long, Homework> repository) {
        this.repository = repository;
        observers = new ArrayList<Observer<HomeworkChangeEvent>>();
    }

    public Iterable<Homework> getAllHomework() {
        return repository.findAll();
    }

    public Homework addHomework(Long id, String description, int deadlineWeek) throws ValidationException {
        Homework s = new Homework(description, deadlineWeek);
        s.setId(id);
        Homework h = repository.save(s);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.ADD, s));
        return h;
    }

    public Homework deleteHomework(Long id) {
        Homework h = repository.delete(id);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.DELETE, h));
        return h;
    }

    public Homework updateHomework(Long id, String description, int deadlineWeek) throws ValidationException {
        Homework old = repository.findOne(id);
        if (old == null)
            throw new ValidationException("Homework id is invalid");
        if (deadlineWeek < Weeks.getCurrentWeek())
            throw new ValidationException("Deadline must be in the future");
        Homework s = new Homework(description, old.getStartWeek(), deadlineWeek);
        s.setId(id);
        Homework h = repository.update(s);
        notifyObservers(new HomeworkChangeEvent(ChangeEventType.UPDATE, s, h));
        return h;
    }

    @Override
    public void addObserver(Observer<HomeworkChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<HomeworkChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(HomeworkChangeEvent t) {
        observers.stream().forEach(obs -> obs.update(t));
    }
}
