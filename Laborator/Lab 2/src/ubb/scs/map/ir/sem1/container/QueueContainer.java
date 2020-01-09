package ubb.scs.map.ir.sem1.container;

import ubb.scs.map.ir.sem1.model.Task;

public class QueueContainer extends AbstractContainer {
    protected int tailIndex;


    public QueueContainer() {
        super();
        tailIndex = 0;
    }

    @Override
    public Task remove() {
        Task task = null;
        if (super.headIndex >= tailIndex) {
            task = tasks[tailIndex];
            tailIndex++;
        }
        return task;
    }

    @Override
    public void add(Task task) {
        super.add(task);
    }

    @Override
    public int size() {
        return headIndex - tailIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        return tailIndex > headIndex;
    }
}
