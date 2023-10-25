package Runners;

import Container.Container;
import Model.Task;

import Container.Container;
import Factory.TaskContainerFactory;
import Model.Task;
import Container.Strategy;


public class StrategyTaskRunner implements TaskRunner{

    private Container container;

    public StrategyTaskRunner(Strategy strategy){
        //single instance
        this.container=TaskContainerFactory.getInstance().createContainer(strategy);
    }

    @Override
    public void executeOneTask() {
        if(!container.isEmpty()){
            container.remove().execute();
        }
    }

    @Override
    public void executeAll() {
        if(!container.isEmpty()){
            executeOneTask();
        }
    }

    @Override
    public void addTask(Task t) {
        container.add(t);
    }

    @Override
    public boolean hasTask() {
        return !container.isEmpty();
    }
}
