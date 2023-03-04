package Container;

import Model.Task;
import Utils.Constants;

public class QueueContainer extends AbstractContainerSuperclass implements Container {

    public QueueContainer() {
        tasks = new Task[Constants.INITIAL_TASK_SIZE];
        size = 0;
    }

    @Override
    public Task remove() {
        if (!isEmpty()) {
            int i;
            Task deletedTask = tasks[0];
            for (i = 0; i < size; i++) {
                tasks[i] = tasks[i + 1];
            }
            --size;
            return deletedTask;
        }
        return null;
    }

}
