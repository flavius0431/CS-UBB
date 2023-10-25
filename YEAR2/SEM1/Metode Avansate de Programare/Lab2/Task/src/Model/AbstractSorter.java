package Model;

public abstract class AbstractSorter extends Task{
    public AbstractSorter(String taskID, String description) {
        super(taskID, description);
    }

    public abstract void sort (SortingMethod sorter);
}
