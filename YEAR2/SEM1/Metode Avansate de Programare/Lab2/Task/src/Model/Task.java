package Model;

import java.util.Objects;

public abstract class  Task {
    private String taskID;
    private String description;

    public Task(String taskID, String description) {
        this.taskID= taskID;
        this.description=description;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Task{" +
                "taskID='" + taskID + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task task)) {
            return false;
        }
        return getTaskID().equals(task.getTaskID()) && getDescription().equals(task.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskID(), getDescription());
    }
}
