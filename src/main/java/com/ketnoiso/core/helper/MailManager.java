package com.ketnoiso.core.helper;

import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * The Class MailManager.
 */
@Service
public class MailManager {
	
	/** The log. */
	private Logger LOG = Logger.getLogger(getClass());
	//public final static String TEMP_DIR = "/opt/tomcat/webapps/ROOT/email/";
	/** The Constant TEMP_DIR. */
	public final static String TEMP_DIR = "c:/temp/email/";
	
	/** The mail sender. */
	private JavaMailSender mailSender;
	
	/** The simple mail message. */
	private SimpleMailMessage simpleMailMessage;

	/**
	 * Sets the simple mail message.
	 * 
	 * @param simpleMailMessage
	 *            the new simple mail message
	 */
	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	/**
	 * Sets the mail sender.
	 * 
	 * @param mailSender
	 *            the new mail sender
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * Send mail.
	 * 
	 * @param dear
	 *            the dear
	 * @param content
	 *            the content
	 */
	public void sendMail(String dear, String content) {

		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(simpleMailMessage.getFrom());
			helper.setTo(simpleMailMessage.getTo());
			helper.setSubject(simpleMailMessage.getSubject());
			helper.setText(String.format(simpleMailMessage.getText(), dear,
					content));

			FileSystemResource file = new FileSystemResource(TEMP_DIR + "log.txt");
			helper.addAttachment(file.getFilename(), file);

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}
		try {
			mailSender.send(message);
		} catch (MailException e) {
			// TODO Auto-generated catch block
			LOG.error(e);
		}
	}
}
