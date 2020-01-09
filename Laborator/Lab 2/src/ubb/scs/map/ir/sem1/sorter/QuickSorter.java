package ubb.scs.map.ir.sem1.sorter;

public class QuickSorter implements Sorter {
    public QuickSorter() {}

    private void pivotSort(int[] elements, int left, int right) {
        if (left >= right)
            return;

        int pivot = (left+right)/2;
        int i = left;
        int j = right;
        do {
            while (i < right && elements[i] < elements[pivot])
                i++;
            while (j > left && elements[j] > elements[pivot])
                j--;

            if (i <= j) {
                int aux = elements[i];
                elements[i] = elements[j];
                elements[j] = aux;
                i++;
                j--;
            }
        } while (i <= j);

        if (i < right)
            pivotSort(elements, i, right);
        if (j > left)
            pivotSort(elements, left, j);
    }

    @Override
    public int[] sort(int[] elements) {
        int[] copy = elements.clone();
        pivotSort(copy, 0, copy.length - 1);
        return copy;
    }
}
