package com.teqto.trackme.email;

import java.io.File;

import com.mashape.unirest.http.exceptions.UnirestException;

public interface EmailInterface {

	public void sendPlainEmail(String emailId, String content);
	public boolean sendAttachmentEmail(String emailId, String content, File file);
	public void sendMailJetEmail(String subject, String body, String toEmail, String toName );
	public void sendOtpMessage(String phone, String content);
	
}
