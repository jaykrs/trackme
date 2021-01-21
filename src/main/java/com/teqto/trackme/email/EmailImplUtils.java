package com.teqto.trackme.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.resource.Emailv31;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONArray;
import org.json.JSONObject;
@Component
public class EmailImplUtils implements EmailInterface{

//	@Autowired
 //   private JavaMailSender javaMailSender;
	
	@Autowired
	private Environment env;
	
	public EmailImplUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sendPlainEmail(String emailId, String content) {
		/*
		 * SimpleMailMessage msg = new SimpleMailMessage(); msg.setTo(emailId);
		 * 
		 * msg.setSubject("Mail from vtrack"); msg.setText(content);
		 * msg.setFrom(ServiceConstants.MAILFROM); javaMailSender.send(msg);
		 */
	}

	public void sendMailJetEmail(String subject, String body, String toEmail, String toName ) {
		 MailjetClient client;
		    MailjetRequest request;
		    MailjetResponse response = null;
		    client = new MailjetClient(env.getProperty("mail.send.apikey"), env.getProperty("mail.send.apisecret"), new ClientOptions("v3.1"));
		    request = new MailjetRequest(Emailv31.resource)
		    .property(Emailv31.MESSAGES, new JSONArray()
		    .put(new JSONObject()
		    .put(Emailv31.Message.FROM, new JSONObject()
		    .put("Email", env.getProperty("mail.send.fromEmail"))
		    .put("Name", env.getProperty("mail.send.fromName")))
		    .put(Emailv31.Message.TO, new JSONArray()
		    .put(new JSONObject()
		    .put("Email", toEmail)
		    .put("Name", toName)))
		    .put(Emailv31.Message.SUBJECT, subject)
		    .put(Emailv31.Message.TEXTPART, env.getProperty("mail.send.vname"))
		    .put(Emailv31.Message.HTMLPART, body)
		    .put(Emailv31.Message.CUSTOMID, toEmail)));
		    try {
				response = client.post(request);
			} catch (MailjetException | MailjetSocketTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(response.getStatus());
	}
	
	@Override
	public boolean sendAttachmentEmail(String emailId, String content, File file) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendOtpMessage(String phone, String content)  {
		try {
		HttpResponse response = Unirest.post(env.getProperty("sms.send.url"))
				  .header("authorization", env.getProperty("sms.send.authkey"))
				  .header("cache-control", "no-cache")
				  .header("content-type", "application/x-www-form-urlencoded")
				  .body("sender_id=FSTSMS&language=english&route=qt&numbers="+phone+"&message=42068&variables={#AA#}&variables_values="+content)
				  .asString();
	}catch(UnirestException ue) {}
	}
}
