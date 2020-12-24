package edu.miu.cs544.emailservice.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class EmailJob implements Job {

    @Autowired
    private Session session;

    public EmailJob() {
    	
    }
    
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String subject = jobDataMap.getString("subject");
        String body = jobDataMap.getString("body");
        String recipientEmail = jobDataMap.getString("email");
        sendMail(recipientEmail, subject, body);
    }

    public void sendMail(String toEmail, String subject, String body) {
    	try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("no-reply", "Notification: No Reply"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            message.setReplyTo(InternetAddress.parse("no-reply"));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("[OK]");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("[NOK]");
        }
    }
}
