package ubb.scs.map.ir.sem1.container;


import ubb.scs.map.ir.sem1.model.Task;

public class StackContainer extends AbstractContainer {

    public StackContainer() {
        super();
    }

    @Override
    public Task remove() {
        if (!isEmpty()) {
            headIndex--;
            return tasks[headIndex];
        }
        return null;
    }

    @Override
    public int size() {
        return headIndex;
    }

    @Override
    public boolean isEmpty() {
        return headIndex ==0;
    }
}
