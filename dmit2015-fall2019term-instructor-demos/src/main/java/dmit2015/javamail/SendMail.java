package dmit2015.javamail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void main(String[] args) {
		Properties props = new Properties();
		Session currentSession = Session.getInstance(props);
		MimeMessage currentMessage = new MimeMessage(currentSession);
		
		Transport currentTransport = null;
		try {
			Address fromAddress = new InternetAddress("mailFromName@gmail.com", "Mail From Name");
			Address toAddress = new InternetAddress("mailTo@destination.com","Mail To Name");
			
			currentMessage.setText("Resistence is futile. You will be assimilated!");
			currentMessage.setFrom(fromAddress);
			currentMessage.setRecipient(Message.RecipientType.TO, toAddress);
			currentMessage.setSubject("You must comply.");
			
			currentTransport = currentSession.getTransport("smtps");
			currentTransport.connect("smtp.gmail.com","yourEmailUsername","yourEmailPassword");
			currentTransport.sendMessage(currentMessage, currentMessage.getAllRecipients());	
			System.out.println("Send mail was successful.");
		} catch (MessagingException | UnsupportedEncodingException ex) {
			ex.printStackTrace();
		} finally {
			if (currentTransport != null) {
				try {
					currentTransport.close();
				} catch (MessagingException ex) {
					
				}
			}
		}

	}

}
