package ubb.scs.map.ir.sem1.container;

import ubb.scs.map.ir.sem1.model.Task;

public abstract class AbstractContainer implements Container {
    protected Task[] tasks;
    protected int headIndex;

    public AbstractContainer() {
        tasks=new Task[10];
        headIndex =-1;
    }

    @Override
    public void add(Task task) {
        if (tasks.length == headIndex + 1) {
            Task t[]=new Task[tasks.length*2];
            System.arraycopy( tasks, 0, t, 0, tasks.length );
            tasks = t;
        }
        headIndex++;
        tasks[headIndex]=task;
    }
}
