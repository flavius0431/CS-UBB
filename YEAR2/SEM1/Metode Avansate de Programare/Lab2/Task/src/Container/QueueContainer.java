package Container;

import Model.Task;
import Utils.Constants;

public class QueueContainer extends AbstractSuperClass implements Container{

    public QueueContainer(){
        tasks =new Task[Constants.INITIAL_TASK_SIZE];
        size=0;
    }

    public Task remove() {
        Task deletedTask = null;
        if (!isEmpty()) {
            int i;
            deletedTask = tasks[0];
            for (i = 0; i < size; i++) {
                tasks[i] = tasks[i + 1];
            }
            --size;
            return deletedTask;
        }
        return null;
    }
}
