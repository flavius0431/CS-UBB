package Factory;
import Container.Container;
import Container.StackContainer;
import Container.QueueContainer;
import Container.Strategy;

public class TaskContainerFactory implements Factory {

    private static TaskContainerFactory instance = new TaskContainerFactory();//singlenton


    public static TaskContainerFactory getInstance(){
        return instance;
    }

    public Container createContainer(Strategy strategy) {
        if (strategy == Strategy.LIFO) {
            return new StackContainer();
        }

        if (strategy == Strategy.FIFO) {
            return new QueueContainer();
        }
        return null;
    }

}
