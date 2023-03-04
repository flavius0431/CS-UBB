package Container;

import Model.Task;
import Utils.Constants;

public class StackContainer extends AbstractContainerSuperclass implements Container {

    public StackContainer() {
        tasks = new Task[Constants.INITIAL_TASK_SIZE];
        size = 0;
    }

    public Task remove() {
        if (!isEmpty()) {
            --size;
            return tasks[size];
        }
        return null;
    }

}
