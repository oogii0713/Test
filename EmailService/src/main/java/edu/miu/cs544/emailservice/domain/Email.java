package edu.miu.cs544.emailservice.domain;

public abstract class Email {

    public abstract String getRecipientAddress();
    public abstract String getSubject();
    public abstract String getBody();
}
