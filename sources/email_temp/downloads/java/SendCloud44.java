package sendcloud.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

// http://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime
// http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient

class AA {
	String address;
	String name;
	String money;

	AA(String address, String name, String money) {
		this.address = address;
		this.name = name;
		this.money = money;
	}
}

public class SendCloudAPIV1_44 {

	public static String convert(List<A> dataList) {

		JSONObject ret = new JSONObject();

		JSONArray to = new JSONArray();

		JSONArray names = new JSONArray();
		JSONArray moneys = new JSONArray();

		for (A a : dataList) {
			to.put(a.address);
			names.put(a.name);
			moneys.put(a.money);
		}

		JSONObject sub = new JSONObject();
		sub.put("%name%", names);
		sub.put("%money%", moneys);

		ret.put("to", to);
		ret.put("sub", sub);

		return ret.toString();
	}

	public static void send_common() throws IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String rcpt_to = "***";

		String subject = "...";
		String html = "...";

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("to", rcpt_to));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.im"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("html", html));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(httpPost);

		// 处理响应
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 正常返回, 解析返回数据
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	public static void send_common_with_attachment() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String rcpt_to = "***";

		String subject = "...";
		String html = "...";

		HttpPost httpPost = new HttpPost(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();;

		// 涉及到附件上传, 需要使用 MultipartEntityBuilder
		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
		entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		entity.setCharset(Charset.forName("UTF-8"));
		ContentType TEXT_PLAIN = ContentType.create("text/plain", Charset.forName("UTF-8"));
		entity.addTextBody("api_user", apiUser, TEXT_PLAIN);
		entity.addTextBody("api_key", apiKey, TEXT_PLAIN);
		entity.addTextBody("to", rcpt_to, TEXT_PLAIN);
		entity.addTextBody("from", "sendcloud@sendcloud.org", TEXT_PLAIN);
		entity.addTextBody("fromname", "SendCloud", TEXT_PLAIN);
		entity.addTextBody("subject", subject, TEXT_PLAIN);
		entity.addTextBody("html", html, TEXT_PLAIN);
		entity.addTextBody("resp_email_id", "true", TEXT_PLAIN);		

		// 添加附件
		File file = new File("D:\\测试.txt");
		String attachName = "附件名称.txt";
		ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
		entity.addBinaryBody("files", file, OCTEC_STREAM, attachName);

		 //添加附件, 文件流形式
//		 File file = new File("D:\\测试.txt");
//		 ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
//		 String attachName = "附件名称.txt";
//		 entity.addBinaryBody("files", new FileInputStream(file), OCTEC_STREAM, attachName);

		 httpPost.setEntity(entity.build());

		 HttpResponse response = httpClient.execute(httpPost);
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

		String subject = "...";

		List<A> dataList = new ArrayList<A>();
		dataList.add(new A("to1@domain.com", "user1", "1000"));
		dataList.add(new A("to2@domain.com", "user2", "2000"));

		final String vars = convert(dataList);
		
		//4.4新实现类CloseableHttpClient
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("substitution_vars", vars));
		params.add(new BasicNameValuePair("template_invoke_name", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(httpPost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	public static void send_template_maillist() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		final String apiUser = "***";
		final String apiKey = "***";
		final String to = "***@maillist.sendcloud.org";

		String subject = "...";
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("api_user", apiUser));
		params.add(new BasicNameValuePair("api_key", apiKey));
		params.add(new BasicNameValuePair("to", to));
		params.add(new BasicNameValuePair("template_invoke_name", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("use_maillist", "true"));
		params.add(new BasicNameValuePair("resp_email_id", "true"));

		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		HttpResponse response = httpClient.execute(httpPost);

		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) { // 正常返回
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpPost.releaseConnection();
	}

	public static void send_template_with_attachment() throws ClientProtocolException, IOException {

		final String url = "http://sendcloud.sohu.com/webapi/mail.send_template.json";

		final String apiUser = "***";
		final String apiKey = "***";

		String subject = "...";

		List<A> dataList = new ArrayList<A>();
		dataList.add(new A("to1@domain.com", "user3", "3000"));
		dataList.add(new A("to2@domain.com", "user4", "4000"));

		final String vars = convert(dataList);
		
		//4.4新实现类CloseableHttpClient
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpost = new HttpPost(url);

		// 涉及到附件上传, 需要使用 MultipartEntityBuilder
		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
		entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		entity.setCharset(Charset.forName("UTF-8"));
		ContentType TEXT_PLAIN = ContentType.create("text/plain", Charset.forName("UTF-8"));
		entity.addTextBody("api_user", apiUser, TEXT_PLAIN);
		entity.addTextBody("api_key", apiKey, TEXT_PLAIN);
		entity.addTextBody("substitution_vars", vars, TEXT_PLAIN);
		entity.addTextBody("template_invoke_name", "test_template", TEXT_PLAIN);
		entity.addTextBody("from", "sendcloud@sendcloud.org", TEXT_PLAIN);
		entity.addTextBody("fromname", "SendCloud", TEXT_PLAIN);
		entity.addTextBody("subject", subject, TEXT_PLAIN);
		entity.addTextBody("resp_email_id", "true", TEXT_PLAIN);		

		// 添加附件
		File file = new File("D:\\测试.txt");
		String attachName = "附件名称.txt";
		ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
		entity.addBinaryBody("files", file, OCTEC_STREAM, attachName);
		
		// 添加附件, 文件流形式
		// File file = new File("D:\\测试.txt");
		//ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
		//String attachName = "附件名称.txt";
		//entity.addBinaryBody("files", new FileInputStream(file), OCTEC_STREAM, attachName);

		httpost.setEntity(entity.build());

		HttpResponse response = httpClient.execute(httpost);
		// 处理响应
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			// 正常返回, 解析返回数据
			System.out.println(EntityUtils.toString(response.getEntity()));
		} else {
			System.err.println("error");
		}
		httpost.releaseConnection();
	}

	public static void main(String[] args) throws Exception {
		//send_common();
		//send_common_with_attachment();
		 send_template_with_attachment();
		 //send_template();
		 //send_template_maillist();
	}
}
