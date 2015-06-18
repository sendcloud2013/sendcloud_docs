package com.sendcloud.example;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class SendCloud {

	public static void send_common() throws IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String rcpt_to = "***";

		HttpPost httpost = new HttpPost(url);
		HttpClient httpclient = new DefaultHttpClient();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("to", rcpt_to));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", "SendCloud java common"));
		params.add(new BasicNameValuePair("html", "欢迎使用SendCloud"));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpclient.execute(httpost);

		// response
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpost.releaseConnection();
	}

	public static void send_common_with_attachment() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String rcpt_to = "***";

		HttpPost httpPost = new HttpPost(url);
		HttpClient httpclient = new DefaultHttpClient();

		// 涉及到附件上传, 需要使用 MultipartEntity
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
		entity.addPart("api_user", new StringBody(apiUser, Charset.forName("UTF-8")));
		entity.addPart("api_key", new StringBody(apiKey, Charset.forName("UTF-8")));
		entity.addPart("to", new StringBody(rcpt_to, Charset.forName("UTF-8")));
		entity.addPart("from", new StringBody("sendcloud@sendcloud.org", Charset.forName("UTF-8")));
		entity.addPart("fromname", new StringBody("SendCloud", Charset.forName("UTF-8")));
		entity.addPart("subject", new StringBody("SendCloud java common attachment", Charset.forName("UTF-8")));
		entity.addPart("html", new StringBody("欢迎使用SendCloud", Charset.forName("UTF-8")));
		entity.addPart("resp_email_id", new StringBody("true"));

		// 添加附件
		File file = new File("/path/file");
		FileBody attachment = new FileBody(file, "application/octet-stream", "UTF-8");
		entity.addPart("files", attachment);

		// 添加附件, 文件流形式
		// File file = new File("/path/file");
		// String attachName = "attach.txt";
		// InputStreamBody is = new InputStreamBody(new FileInputStream(file),
		// attachName);
		// entity.addPart("files", is);

		httpPost.setEntity(entity);

		HttpResponse response = httpclient.execute(httpPost);
		// 处理响应
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 正常返回, 解析返回数据
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	public static void send_template() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String vars = "{\"to\": [\"to1@domain.com\", \"to2@domain.com\"], \"sub\" : { \"%name%\" : [\"user1\", \"user2\"], \"%money%\" : [\"1000\", \"2000\"]}}";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("substitution_vars", vars));
		params.add(new BasicNameValuePair("template_invoke_name", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", "SendCloud java template"));
		params.add(new BasicNameValuePair("html", "欢迎使用SendCloud"));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpclient.execute(httpost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
	}

	public static void send_template_maillist() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String to = "test@maillist.sendcloud.org";

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("to", to));
		params.add(new BasicNameValuePair("template_invoke_name", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", "SendCloud java template maillist"));
		params.add(new BasicNameValuePair("use_maillist", "true"));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpclient.execute(httpost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
	}

	public static void main(String[] args) throws ClientProtocolException, IOException {
		// send_common();
		// send_common_with_attachment();
		// send_template();
		// send_template_maillist();
	}
}
