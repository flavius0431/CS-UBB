package Runners;


public class DelayTaskRunner extends AbstractTaskRunner{

    public DelayTaskRunner(TaskRunner runner) {
        super(runner);
    }

    @Override
    public void executeOneTask() {
        delayedTaskRunner();
    }

    private void delayedTaskRunner() {
        try{
            Thread.sleep(3000);
            runner.executeOneTask();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
