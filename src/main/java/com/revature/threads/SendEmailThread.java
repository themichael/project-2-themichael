package com.revature.threads;

import javax.mail.MessagingException;

import com.revature.service.SendEmailService;
import com.revature.util.LogUtil;

public class SendEmailThread extends Thread {
	
	SendEmailService service;
	String subject;
	String messageText;
	
	public SendEmailThread(SendEmailService service, String subject, String messageText) {
		this.service = service;
		this.subject = subject;
		this.messageText = messageText;
	}
	
	@Override
	public void run() {
		try {
			service.sendMail(subject, messageText);
		} catch (MessagingException e) {
			LogUtil.logger.warn("An error has been produced sending an email", e);
		}
	}
}
