package com.te.golms.service.implementation;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.te.golms.service.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImplementation implements EmailService {

	private static final String FROM_EMAIL = "thexplorerpro@gmail.com";
	private static final String EMAIL_PASSWORD = "SomeRandomPassoword@123";

	@Override
	public Boolean sendEmail(List<String> toEmails, String emailSubject, String emailContent) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM_EMAIL, EMAIL_PASSWORD);
			}
		});
		session.setDebug(true);
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROM_EMAIL));
			if (toEmails.size() < 2 && !toEmails.isEmpty()) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails.get(0)));
			} else {
				for (String toEmail : toEmails) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(toEmail));
				}
			}
			message.setSubject(emailSubject);
			message.setContent(emailContent, "text/html");
			log.info("Sending message!");
			Transport.send(message);
			log.info("Message sent!");
			return true;
		} catch (MessagingException mex) {
			return false;
		}
	}

}
