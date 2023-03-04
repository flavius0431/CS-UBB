package Model;

import Container.Container;
import Utils.Constants;

import javax.swing.*;

public class SortingTask extends AbstractSorter {
    public int[] vector_to_sort;

    public SortingTask(String taskId, String description) {
        super(taskId, description);
    }

    public SortingTask(int[] toSort) {
        super("0", "");
        vector_to_sort = toSort;
    }


    @Override
    public void sort(SortingMethod sorter) {
        if (sorter == SortingMethod.BUBBLE) {
            bubbleSort();
        }
        if (sorter == SortingMethod.QUICK) {
            quickSort();
        }
    }

    public void bubbleSort() {
        int n = vector_to_sort.length;
        int temp = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (vector_to_sort[j - 1] > vector_to_sort[j]) {
                    temp = vector_to_sort[j - 1];
                    vector_to_sort[j - 1] = vector_to_sort[j];
                    vector_to_sort[j] = temp;
                }
            }
        }
        execute();
    }

    public void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    public void quickSort() {
        quickSort(vector_to_sort, 0, vector_to_sort.length - 1);
        execute();
    }

    @Override
    public void execute() {
        System.out.println();
        for (int elem : vector_to_sort) {
            System.out.print(elem + " ");
        }
    }
}
