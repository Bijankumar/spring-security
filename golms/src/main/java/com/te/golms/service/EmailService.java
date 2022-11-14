package com.te.golms.service;

import java.util.List;

public interface EmailService {
	public abstract Boolean sendEmail(List<String> toEmails, String emailSubject, String emailContent);
}
