package ubb.scs.map.ir.sem1;

import ubb.scs.map.ir.sem1.container.Container;
import ubb.scs.map.ir.sem1.container.Strategy;
import ubb.scs.map.ir.sem1.factory.TaskContainerFactory;
import ubb.scs.map.ir.sem1.model.MessageTask;
import ubb.scs.map.ir.sem1.model.SortingTask;
import ubb.scs.map.ir.sem1.runner.*;
import ubb.scs.map.ir.sem1.sorter.SortingMethod;

import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.Random;


class TestTaskRunner {
    private AbstractTaskRunner atr;
    private static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    private static String generateRandomString(int length) {
        Random r = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += alphabet.charAt(r.nextInt(26));
        }
        return result;
    }

    private static int[] generateRandomArray(int length) {
        Random r = new Random();
        int[] arr = new int[length];
        for (int j = 0; j < length; j++)
            arr[j] = r.nextInt(1000000000);
        return arr;
    }

    public TestTaskRunner(AbstractTaskRunner atr) {
        this.atr = atr;
    }

    public void addTests(int amount) {
        Random r = new Random();
        for (int i = 1; i <= amount; i++) {
            if (i%2 == 0)
                atr.addTask(new MessageTask(Integer.toString(i), generateRandomString(8),
                    generateRandomString(8), generateRandomString(8), generateRandomString(8),
                    generateRandomString(8), LocalDateTime.now()));
            else {
                int n = r.nextInt(600);
                atr.addTask(new SortingTask(Integer.toString(i), generateRandomString(8),
                        generateRandomArray(n), SortingMethod.QUICKSORT));
            }
        }
    }

    public void runTests() {
        atr.executeAll();
    }
}

public class TestMain {

    public static Strategy getStrategy(String arg) {
        if (arg.toLowerCase().equals("lifo"))
            return Strategy.LIFO;
        if (arg.toLowerCase().equals("fifo"))
            return Strategy.FIFO;
        return null;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid argument count.\nUsage: TestMain <strategy_to_use>\n");
            System.exit(1);
        }
        Strategy s = getStrategy(args[0]);
        if (s == null) {
            System.out.println("Invalid strategy!");
            System.exit(2);
        }

        StrategyTaskRunner str = new StrategyTaskRunner(s);

        PrinterTaskRunner ptr = new PrinterTaskRunner(str);
        DelayTaskRunner dtr = new DelayTaskRunner(str);

        TestTaskRunner ttr = new TestTaskRunner(ptr);
        ttr.addTests(6);
        ttr.runTests();

        TestTaskRunner delayTtr = new TestTaskRunner(dtr);
        delayTtr.addTests(8);
        delayTtr.runTests();
        //System.out.println(ptr);
    }
}
