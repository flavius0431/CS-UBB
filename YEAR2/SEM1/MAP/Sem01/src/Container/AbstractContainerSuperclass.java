package Container;

import Model.Task;

public class AbstractContainerSuperclass {
    protected Task[] tasks;
    protected int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Task task) {
        if (tasks.length == size) {
            Task[] t = new Task[tasks.length * 2];
            System.arraycopy(tasks, 0, t, 0, tasks.length);
            tasks = t;
        }
        tasks[size] = task;
        ++size;
    }
}
