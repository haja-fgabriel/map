package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.container.*;


// TODO implement class using the other design pattern

public class TaskContainerFactory implements Factory<Container, Strategy> {
    //private static final Factory INSTANCE = new TaskContainerFactory();
    private static Factory instance = null;
    private TaskContainerFactory() {}

    public static Factory<Container, Strategy> getInstance() {
        if (instance == null)
            instance = new TaskContainerFactory();
        return instance;
    }


    @Override
    public Container create(Strategy strategy) {
        switch(strategy) {
            case FIFO: return new QueueContainer();
            case LIFO: return new StackContainer();
            default:   return null;
        }
    }

}
