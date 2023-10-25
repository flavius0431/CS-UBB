package Model;

public abstract class AbstractSorter extends Task{

    public AbstractSorter(String taskId, String description) {
        super(taskId, description);
    }

    public abstract void sort(SortingMethod sorter);
}
