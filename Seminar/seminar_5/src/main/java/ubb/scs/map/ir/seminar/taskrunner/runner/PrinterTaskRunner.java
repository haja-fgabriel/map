package ubb.scs.map.ir.seminar.taskrunner.runner;

import ubb.scs.map.ir.seminar.taskrunner.utils.Constants;

import java.time.LocalDateTime;

public class PrinterTaskRunner extends AbstractTaskRunner {
    public PrinterTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        runner.executeOneTask();
        decorateExecuteOneTask();
     }

    public void decorateExecuteOneTask(){
        System.out.println("task executat la: " + LocalDateTime.now().format(Constants.DATE_TIME_FORMATTER));
    }
}
