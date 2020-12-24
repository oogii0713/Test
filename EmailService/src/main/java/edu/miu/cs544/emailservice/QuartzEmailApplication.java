package edu.miu.cs544.emailservice;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class QuartzEmailApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(QuartzEmailApplication.class, args);
	}
	
	@Bean
    @QuartzDataSource
    public DataSource quartzDataSource() {
        return DataSourceBuilder.create()
        		.url("jdbc:mysql://localhost/cs544_project_email")
        		.username("cs544")
        		.password("201511")
        		.driverClassName("com.mysql.cj.jdbc.Driver")
           		.build();
    }
	
	@Autowired
	private MailProperties mailProperties;
	
	@Bean("mailProperties")
	public MailProperties getMailProperties() {
		MailProperties mailProperties = new MailProperties();
		mailProperties.setUsername("testing.email.service.cs544@gmail.com");
		mailProperties.setPassword("leavenbread4");
		return mailProperties;
	}
	
	@Bean("session")
	public Session getSession() {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUsername(), mailProperties.getPassword());
            }
        });
        if (session == null) System.exit(1);
        return session;
	}
}
