package ubb.scs.map.ir.sem1.model;

import ubb.scs.map.ir.sem1.utils.Constants;

import java.time.LocalDateTime;

public class Message {
    private String subject;
    private String body;
    private String from;
    private String to;
    private LocalDateTime date;

    public Message(String subject, String body, String from, String to, LocalDateTime date) {
        this.subject = subject;
        this.body = body;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    @Override
    public String toString() {
        return "message=" + body + "|from=" + from + "|to=" + to + "|date=" + date.format(Constants.DATE_TIME_FORMATTER);
    }
}
