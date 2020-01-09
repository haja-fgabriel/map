package ubb.scs.map.ir.sem1.model;

import ubb.scs.map.ir.sem1.factory.SorterFactory;
import ubb.scs.map.ir.sem1.sorter.Sorter;
import ubb.scs.map.ir.sem1.sorter.SortingMethod;

public class SortingTask extends Task {
    private int[] elements;
    private Sorter sorter;

    public SortingTask(String taskId, String description, int[] elements, SortingMethod method) {
        super(taskId, description);
        sorter = SorterFactory.getInstance().create(method);
        this.elements = elements;
    }

    @Override
    public void execute() {
        int[] sorted = sorter.sort(elements);
        System.out.print("Sorted elements: [ ");
        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i]);
            if (i < sorted.length - 1)
                System.out.print(", ");
        }
        System.out.println(" ]");
    }
}
