package com.revature.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmailService {

	private String to;
	private String from;
	
	private static String email = System.getenv("ERS_EMAIL");
	private static String password = System.getenv("ERS_PASSWORD");
	
	private Authenticator authenticator = new Authenticator() {
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(email, password);
	    }
	};
	
	private Properties properties = System.getProperties();
	{
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.port", "587");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
	}
	
	Session session = Session.getInstance(properties, authenticator);
	
	public SendEmailService(String to) {
		this.to = to;
		this.from = email;
	}
	
	public void sendMail(String subject, String messageText) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(messageText);
        Transport.send(message);
	}
}
