package ubb.scs.map.ir.seminar.taskrunner.container;

import ubb.scs.map.ir.seminar.taskrunner.model.Task;

public class QueueContainer implements Container {
    @Override
    public Task remove() {
        return null;
    }

    @Override
    public void add(Task task) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
