### WEBAPI

```
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class JavaWebapi {

    /**
     * @param args
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {

        String url = "http://sendcloud.sohu.com/webapi/mail.send.json";
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpost = new HttpPost(url);

        List nvps = new ArrayList();
        nvps.add(new BasicNameValuePair("api_user", "***")); # 使用api_user和api_key进行验证
        nvps.add(new BasicNameValuePair("api_key", "***"));
        nvps.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org")); # 发信人，用正确邮件地址替代
        nvps.add(new BasicNameValuePair("to", "to1@domain.com;to2@domain.com")); # 收件人地址，用正确邮件地址替代，多个地址用';'分隔
        nvps.add(new BasicNameValuePair("subject", "SendCloud java webapi example"));
        nvps.add(new BasicNameValuePair("html", "<html><head></head><body><p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud</a></p></body></html>"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        HttpResponse response = httpclient.execute(httpost);

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
            System.out.println(EntityUtils.toString(response.getEntity()));
        } else {
            System.err.println("error");
        }

    }

}
```

- - -

### SMTP

```
package com.sohu.sendCloud.examples;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class JavaSmtpExample {

    private static final  String SENDCLOUD_SMTP_HOST = "smtpcloud.sohu.com";
    private static final  int SENDCLOUD_SMTP_PORT = 25;

    /**
     * @param args
     * @throws MessagingException 
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws MessagingException, UnsupportedEncodingException {

        // 配置javamail
        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SENDCLOUD_SMTP_HOST);
        props.put("mail.smtp.port", SENDCLOUD_SMTP_PORT);
        props.setProperty("mail.smtp.auth", "true");
        props.put("mail.smtp.connectiontimeout", 180);
        props.put("mail.smtp.timeout", 600);

        props.setProperty("mail.mime.encodefilename", "true");

        // 使用api_user和api_key进行验证
        final String apiUser = "***";
        final String apiKey = "***";

        Session mailSession = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(apiUser, apiKey);
            }
        });

        Transport transport = mailSession.getTransport();
        MimeMessage message = new MimeMessage(mailSession);
        Multipart multipart = new MimeMultipart("alternative");

        // 添加html形式的邮件正文
        BodyPart part1 = new MimeBodyPart();
        part1.setHeader("Content-Type", "text/html;charset=UTF-8");
        part1.setHeader("Content-Transfer-Encoding", "base64");
        String htmlContent = "<html><head></head><body>" +
                "<p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud!</a></p>" +
                "</body></html> ";
        part1.setContent(htmlContent, "text/html;charset=UTF-8");
        multipart.addBodyPart(part1);
        message.setContent(multipart);

        // 发信人，用正确邮件地址替代 
        message.setFrom(new InternetAddress("from@sendcloud.org", "fromname", "UTF-8"));
        // 收件人地址，用正确邮件地址替代
        message.addRecipient(RecipientType.TO, new InternetAddress("to@sendcloud.org"));
        // 邮件主题
        message.setSubject("SendCloud Java Smtp Example", "UTF-8");

        // 连接sendcloud服务器，发送邮件
        transport.connect();
        transport.sendMessage(message, message.getRecipients(RecipientType.TO));
        transport.close();

    }
}
```
