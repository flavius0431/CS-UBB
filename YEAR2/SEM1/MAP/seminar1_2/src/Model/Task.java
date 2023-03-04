package Model;

import java.util.Objects;

public abstract class Task {

    private String TaskID;
    private String descriere;

    public String getTaskID() {
        return TaskID;
    }

    public void setTaskID(String taskID) {
        TaskID = taskID;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Task(String taskID,String descriere) {
        TaskID = taskID;
        this.descriere = descriere;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Task{" +
                "TaskID='" + TaskID + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Task task = (Task) o;
        return TaskID.equals(task.TaskID) && descriere.equals(task.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(TaskID, descriere);
    }

}
