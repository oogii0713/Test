package edu.miu.cs544.emailservice.domain;


public class UpcomingFlightEmail extends Email {

    private String recipientAddress;
    private String subject;
    private String body;

    public UpcomingFlightEmail() {

    }

    public UpcomingFlightEmail(String recipientAddress, String subject, String body) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public String getRecipientAddress() {
        return this.recipientAddress;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    @Override
    public String getBody() {
        return this.body;
    }
}
