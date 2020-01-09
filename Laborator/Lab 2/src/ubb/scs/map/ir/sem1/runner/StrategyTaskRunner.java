package ubb.scs.map.ir.sem1.runner;

import ubb.scs.map.ir.sem1.container.Container;
import ubb.scs.map.ir.sem1.container.Strategy;
import ubb.scs.map.ir.sem1.factory.TaskContainerFactory;
import ubb.scs.map.ir.sem1.model.Task;

public class StrategyTaskRunner implements TaskRunner {
    private Container container;

    public StrategyTaskRunner(Strategy strategy) {
        this.container = TaskContainerFactory.getInstance().create(strategy);
    }

    @Override
    public void executeOneTask() {
        container.remove().execute();
    }

    @Override
    public void executeAll() {
        while (!container.isEmpty())
            executeOneTask();
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return (!container.isEmpty());
    }
}
