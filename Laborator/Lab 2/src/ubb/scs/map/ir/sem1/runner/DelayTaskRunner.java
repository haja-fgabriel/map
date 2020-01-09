package ubb.scs.map.ir.sem1.runner;

// TODO finish implementation of DelayTaskRunner


public class DelayTaskRunner extends AbstractTaskRunner {
    public DelayTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        try{
            Thread.sleep(3000);
            super.executeOneTask();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
}
