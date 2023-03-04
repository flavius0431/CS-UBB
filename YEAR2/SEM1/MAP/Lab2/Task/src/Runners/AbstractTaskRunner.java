package Runners;
import Model.Task;

public abstract class AbstractTaskRunner implements TaskRunner {

    protected TaskRunner runner;

    public AbstractTaskRunner(TaskRunner runner) {
        this.runner = runner;
    }


    public abstract void executeOneTask();

    public void executeAll(){
        while(runner.hasTask()){
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        runner.addTask(t);
    }

    @Override
    public boolean hasTask() {
        return runner.hasTask();
    }
}
