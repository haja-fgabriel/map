package ubb.scs.map.ir.sem1.runner;

import ubb.scs.map.ir.sem1.model.Task;

public interface TaskRunner {
    void executeOneTask();
    void executeAll();
    void addTask(Task t);
    boolean hasTask();
}
