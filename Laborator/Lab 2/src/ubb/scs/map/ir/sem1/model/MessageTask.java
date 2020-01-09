package ubb.scs.map.ir.sem1.model;

import ubb.scs.map.ir.sem1.utils.Constants;

import java.time.LocalDateTime;

public class MessageTask extends Task {
    private Message message;

    public MessageTask(String taskId, String description, String subject, String body, String from, String to, LocalDateTime date) {
        super(taskId, description);
        message = new Message(subject, body, from, to, date);
    }

    @Override
    public void execute() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return "id=" + this.getTaskId() + "|description=" + this.getDescription() +
                "|" + message.toString();
    }
}
