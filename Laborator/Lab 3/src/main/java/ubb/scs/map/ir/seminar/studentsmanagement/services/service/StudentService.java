package ubb.scs.map.ir.seminar.studentsmanagement.services.service;

import ubb.scs.map.ir.seminar.studentsmanagement.domain.Student;
import ubb.scs.map.ir.seminar.studentsmanagement.domain.validators.ValidationException;
import ubb.scs.map.ir.seminar.studentsmanagement.repository.file.FileRepository;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.ChangeEventType;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.events.StudentChangeEvent;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observable;
import ubb.scs.map.ir.seminar.studentsmanagement.utils.observer.Observer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


// TODO - finish implementation of this service
public class StudentService implements Observable<StudentChangeEvent> {
    private FileRepository<Long, Student> repository;
    private List<Observer<StudentChangeEvent>> observers;

    public StudentService(FileRepository<Long, Student> repository) {
        this.repository = repository;
        observers = new ArrayList<>();
    }

    public Iterable<Student> getAllStudents() {
        return repository.findAll();
    }

    public Student addStudent(Long id, String name, float media, String group) throws ValidationException {
        Student s = new Student(name, media, group);
        s.setId(id);
        Student s2 = repository.save(s);
        this.notifyObservers(new StudentChangeEvent(ChangeEventType.ADD, s2));
        return s2;
    }

    public Student deleteStudent(Long id) {
        Student s = repository.delete(id);
        notifyObservers(new StudentChangeEvent(ChangeEventType.DELETE, s));
        return s;
    }

    public Student updateStudent(Long id, String name, float media, String group) throws ValidationException {
        Student s = new Student(name, media, group);
        s.setId(id);
        Student s2 = repository.update(s);
        this.notifyObservers(new StudentChangeEvent(ChangeEventType.UPDATE, s2, s));
        return s2;
    }

    public Iterable<Student> getStudentsByGroup(String group) {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .filter(student -> student.getGroup().equals(group))
                .collect(Collectors.toList());
    }


    @Override
    public void addObserver(Observer<StudentChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<StudentChangeEvent> e) {
        ;
    }

    @Override
    public void notifyObservers(StudentChangeEvent t) {
        observers.stream().forEach(x->x.update(t));
    }
}
