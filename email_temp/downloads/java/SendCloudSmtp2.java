package com.sendcloud.example;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

import com.sun.mail.smtp.SMTPTransport;

class Xsmtpapi {

	List<String> toList;

	Map<String, List<String>> subMap;

	Map<String, String> sectionMap;

	JSONObject x = new JSONObject();

	public Xsmtpapi(List<String> toList) {
		this.toList = toList;
		x.put("to", toList);
	}

	public Xsmtpapi(List<String> toList, Map<String, List<String>> subMap) {
		this.toList = toList;
		this.subMap = subMap;
		x.put("to", toList);
		x.put("sub", subMap);
	}

	public Xsmtpapi(List<String> toList, Map<String, List<String>> subMap, Map<String, String> sectionMap) {
		this.toList = toList;
		this.subMap = subMap;
		this.sectionMap = sectionMap;
		x.put("to", toList);
		x.put("sub", subMap);
		x.put("section", sectionMap);
	}

	@Override
	public String toString() {
		return x.toString();
	}
}

public class SendCloudSmtp2 {

	private static final String SENDCLOUD_SMTP_HOST = "smtp.sendcloud.net";
	private static final int SENDCLOUD_SMTP_PORT = 25;

	private static String getMessage(String reply) {
		String[] arr = reply.split("#");

		String messageId = null;

		if (arr[0].equalsIgnoreCase("250 ")) {
			messageId = arr[1];
		}

		return messageId;
	}

	public static void send(final String apiUser, final String apiKey, String to, String replyTo, Xsmtpapi xsmtpapi) throws MessagingException,
			UnsupportedEncodingException {

		// 配置javamail
		Properties props = System.getProperties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", SENDCLOUD_SMTP_HOST);
		props.put("mail.smtp.port", SENDCLOUD_SMTP_PORT);
		props.setProperty("mail.smtp.auth", "true");
		props.put("mail.smtp.connectiontimeout", 180);
		props.put("mail.smtp.timeout", 600);
		props.setProperty("mail.mime.encodefilename", "true");

		Session mailSession = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(apiUser, apiKey);
			}
		});

		SMTPTransport transport = (SMTPTransport) mailSession.getTransport("smtp");

		MimeMessage message = new MimeMessage(mailSession);
		// 发信人
		message.setFrom(new InternetAddress("from@sendcloud.org", "fromname", "UTF-8"));
		// 收件人地址
		message.addRecipient(RecipientType.TO, new InternetAddress(to));
		// 邮件主题
		message.setSubject("SendCloud java smtp xsmtpapi example", "UTF-8");

		// 设置邮件回复
		message.setReplyTo(new Address[] { new InternetAddress(replyTo) });

		// 设置 xsmtpapi
		System.out.println(xsmtpapi.toString());
		message.setHeader("X-SMTPAPI", new String(Base64.encodeBase64(xsmtpapi.toString().getBytes())));

		Multipart multipart = new MimeMultipart("alternative");

		// 添加html形式的邮件正文
		String html = "<html><head></head><body>" + "<p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a> %email%  %name%</p>" + "</body></html> ";
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setHeader("Content-Type", "text/html;charset=UTF-8");
		contentPart.setHeader("Content-Transfer-Encoding", "base64");
		contentPart.setContent(html, "text/html;charset=UTF-8");
		multipart.addBodyPart(contentPart);

		// 添加附件 ( smtp 方式没法使用文件流 )
		File file = new File("/path/file");
		BodyPart attachmentBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(file);
		attachmentBodyPart.setDataHandler(new DataHandler(source));
		attachmentBodyPart.setFileName(MimeUtility.encodeWord(file.getName()));
		multipart.addBodyPart(attachmentBodyPart);

		message.setContent(multipart);

		// 连接sendcloud服务器，发送邮件
		transport.connect();
		transport.sendMessage(message, message.getRecipients(RecipientType.TO));

		String messageId = getMessage(transport.getLastServerResponse());
		String emailId = messageId + "0$" + to;
		System.out.println("messageId:" + messageId);
		System.out.println("emailId:" + emailId);
		transport.close();
	}

	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {

		String apiUser = "";
		String apiKey = "";

		List<String> toList = new ArrayList<String>();
		toList.add("to1@ifaxin.com");
		toList.add("to2@ifaxin.com");

		Map<String, List<String>> subMap = new HashMap<String, List<String>>();
		subMap.put("%email%", toList);
		subMap.put("%name%", new ArrayList<String>(Arrays.asList("name1 %num1%", "name2 %num2%")));

		Map<String, String> sectionMap = new HashMap<String, String>();
		sectionMap.put("%num1%", "1111111111111111111");
		sectionMap.put("%num2%", "2222222222222222222");

		Xsmtpapi xsmtpapi = new Xsmtpapi(toList, subMap, sectionMap);
		
		String replyTo = "reply@ifaxin.com";
		send(apiUser, apiKey, toList.get(0), replyTo, xsmtpapi);
	}

}
