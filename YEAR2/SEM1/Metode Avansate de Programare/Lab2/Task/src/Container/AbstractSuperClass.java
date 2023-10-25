package Container;

import Model.Task;

public abstract class AbstractSuperClass implements Container{
    protected Task[] tasks;
    protected int size;
    public int size(){return size;}
    public boolean isEmpty(){return size==0;}

    public void add(Task task){
        if(tasks.length==size){
            Task[] t = new Task[tasks.length*2];
            System.arraycopy(tasks,0,0,0,tasks.length);
            tasks = t;
        }
        tasks[size] = task;
        ++size;
    }
    public abstract Task remove();
}
