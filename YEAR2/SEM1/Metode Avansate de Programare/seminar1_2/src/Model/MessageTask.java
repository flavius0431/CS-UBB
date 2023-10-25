package Model;
import java.time.LocalDateTime;

public class MessageTask extends Task {
    private String message;
    private String from;
    private String to;
    private LocalDateTime data;

    public MessageTask(String message, String from, String to, LocalDateTime data){
        super();

    }

    public MessageTask(String taskID, String descriere) {
        super(taskID, descriere);
    }

    @Override
    public void execute() {

    }
}
