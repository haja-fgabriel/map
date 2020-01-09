package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.sorter.Sorter;
import ubb.scs.map.ir.sem1.sorter.BubbleSorter;
import ubb.scs.map.ir.sem1.sorter.QuickSorter;
import ubb.scs.map.ir.sem1.sorter.SortingMethod;

public class SorterFactory implements Factory<Sorter, SortingMethod> {
    private static SorterFactory factory;

    private SorterFactory() {}

    public static Factory<Sorter, SortingMethod> getInstance() {
        if (factory == null)
            factory = new SorterFactory();
        return factory;
    }

    @Override
    public Sorter create(SortingMethod elementType) {
        switch (elementType) {
            case QUICKSORT:     return new QuickSorter();
            case BUBBLESORT:    return new BubbleSorter();
            default:            return null;
        }
    }
}
