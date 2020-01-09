package ubb.scs.map.ir.sem1.sorter;

public class BubbleSorter implements Sorter {
    public BubbleSorter() {}

    @Override
    public int[] sort(int[] elements) {
        int[] copy = elements.clone();
        boolean repeat = true;
        do {
            repeat = false;
            for (int i = 0; i < elements.length - 1; i++)
                if (copy[i] > copy[i+1]) {
                    copy[i] ^= copy[i+1];
                    copy[i+1] ^= copy[i];
                    copy[i] ^= copy[i+1];
                    repeat = true;
                }
        } while (repeat);

        return copy;
    }
}
