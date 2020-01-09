package ubb.scs.map.ir.sem1;
import ubb.scs.map.ir.sem1.container.Strategy;
import ubb.scs.map.ir.sem1.model.MessageTask;
import ubb.scs.map.ir.sem1.model.SortingTask;
import ubb.scs.map.ir.sem1.runner.StrategyTaskRunner;
import ubb.scs.map.ir.sem1.runner.TaskRunner;
import ubb.scs.map.ir.sem1.sorter.SortingMethod;

import java.time.LocalDateTime;

public class Main {

    public static MessageTask[] createMessageTaskArray(){
        MessageTask t1=new MessageTask("1","Feedback lab1",
            "Nota obtinuta:", "Felicitari, ba! 9.60!",
                "Gigi", "Ana", LocalDateTime.now());
        MessageTask t2=new MessageTask("2","Feedback lab1",
                "Nota obtinuta:", "Ai obtinut 5.60. Lasa, data viitoare va fi mai bine",
                "Gigi", "Ana", LocalDateTime.now());
        MessageTask t3=new MessageTask("3","Feedback lab3",
                "Nota obtinuta:", "Ai obtinut 10, felicitari!",
                "Gigi", "Ana", LocalDateTime.now());
        return new MessageTask[]{t1,t2,t3};
    }
    public static void main(String[] args) {
        MessageTask[] l=createMessageTaskArray();
        TaskRunner runner = new StrategyTaskRunner(Strategy.LIFO);
        runner.addTask(l[0]);
        runner.addTask(l[1]);
        runner.addTask(l[2]);
        runner.executeAll();


        int[] elems = {4,3,2,1,5};
        SortingTask task = new SortingTask("1234", "Sorting 5 elements in main", elems, SortingMethod.QUICKSORT);
        SortingTask task2 = new SortingTask("1234", "Sorting 5 elements in main", elems, SortingMethod.BUBBLESORT);

        task.execute();
        task2.execute();

    }

}


