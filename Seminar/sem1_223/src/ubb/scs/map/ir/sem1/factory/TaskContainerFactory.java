package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.container.Container;
import ubb.scs.map.ir.sem1.container.StackContainer;
import ubb.scs.map.ir.sem1.container.Strategy;


// TODO implement class using the other design pattern

public class TaskContainerFactory implements Factory {
    //private static final Factory INSTANCE = new TaskContainerFactory();
    private static Factory instance = null;
    private TaskContainerFactory() {}

    public static Factory getInstance() {
        if (instance == null)
            instance = new TaskContainerFactory();
        return instance;
    }


    @Override
    public Container createContainer(Strategy strategy) {
        switch(strategy) {
            case FIFO: //return new StackContainer();
            case LIFO: return new StackContainer();
            default:   return null;
        }
    }

}
