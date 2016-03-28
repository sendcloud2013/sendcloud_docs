package sendcloud.example;

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
import org.json.JSONArray;
import org.json.JSONObject;

public class SendCloudAPIV2 {

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

		final String url = "http://api.sendcloud.net/apiv2/mail/send";

		final String apiUser = "...";
		final String apiKey = "...";
		final String rcpt_to = "...";

		String subject = "...";
		String html = "...";

		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", apiUser));
		params.add(new BasicNameValuePair("apiKey", apiKey));
		params.add(new BasicNameValuePair("to", rcpt_to));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("html", html));

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

		final String url = "http://api.sendcloud.net/apiv2/mail/send";

		final String apiUser = "***";
		final String apiKey = "***";
		final String rcpt_to = "***";

		String subject = "...";
		String html = "...";

		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = new DefaultHttpClient();

		// 涉及到附件上传, 需要使用 MultipartEntity
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
				Charset.forName("UTF-8"));
		entity.addPart("apiUser", new StringBody(apiUser, Charset.forName("UTF-8")));
		entity.addPart("apiKey", new StringBody(apiKey, Charset.forName("UTF-8")));
		entity.addPart("to", new StringBody(rcpt_to, Charset.forName("UTF-8")));
		entity.addPart("from", new StringBody("sendcloud@sendcloud.org", Charset.forName("UTF-8")));
		entity.addPart("fromname", new StringBody("SendCloud", Charset.forName("UTF-8")));
		entity.addPart("subject", new StringBody(subject, Charset.forName("UTF-8")));
		entity.addPart("html", new StringBody(html, Charset.forName("UTF-8")));

		// 添加附件
		File file = new File("D:\\1.txt");
		FileBody attachment = new FileBody(file, "application/octet-stream", "UTF-8");
		entity.addPart("attachments", attachment);

		// 添加附件, 文件流形式
		// File file = new File("D:\\1.txt");
		// String attachName = "attach.txt";
		// InputStreamBody is = new InputStreamBody(new FileInputStream(file),
		// attachName);
		// entity.addPart("attachments", is);

		httpPost.setEntity(entity);

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

		final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";

		final String apiUser = "***";
		final String apiKey = "***";

		String subject = "...";

		List<A> dataList = new ArrayList<A>();
		dataList.add(new A("to1@ifaxin.com", "user1", "1000"));
		dataList.add(new A("to2@ifaxin.com", "user2", "2000"));

		final String xsmtpapi = convert(dataList);

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", apiUser));
		params.add(new BasicNameValuePair("apiKey", apiKey));
		params.add(new BasicNameValuePair("xsmtpapi", xsmtpapi));
		params.add(new BasicNameValuePair("templateInvokeName", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));

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

		final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";

		final String apiUser = "***";
		final String apiKey = "***";
		// to is addressList
		final String to = "***";

		String subject = "...";

		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", apiUser));
		params.add(new BasicNameValuePair("apiKey", apiKey));
		params.add(new BasicNameValuePair("to", to));
		params.add(new BasicNameValuePair("templateInvokeName", "test_template"));
		params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
		params.add(new BasicNameValuePair("fromname", "SendCloud"));
		params.add(new BasicNameValuePair("subject", subject));
		params.add(new BasicNameValuePair("useAddressList", "true"));

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

		final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";

		final String apiUser = "***";
		final String apiKey = "***";

		String subject = "...";

		List<A> dataList = new ArrayList<A>();
		dataList.add(new A("to1@ifaxin.com", "user1", "1000"));
		dataList.add(new A("to2@ifaxin.com", "user2", "2000"));

		final String xsmtpapi = convert(dataList);

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);

		// 涉及到附件上传, 需要使用 MultipartEntity
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null,
				Charset.forName("UTF-8"));
		entity.addPart("apiUser", new StringBody(apiUser, Charset.forName("UTF-8")));
		entity.addPart("apiKey", new StringBody(apiKey, Charset.forName("UTF-8")));
		entity.addPart("xsmtpapi", new StringBody(xsmtpapi, Charset.forName("UTF-8")));
		entity.addPart("templateInvokeName", new StringBody("test_template", Charset.forName("UTF-8")));
		entity.addPart("from", new StringBody("sendcloud@sendcloud.org", Charset.forName("UTF-8")));
		entity.addPart("fromname", new StringBody("SendCloud", Charset.forName("UTF-8")));
		entity.addPart("subject", new StringBody(subject, Charset.forName("UTF-8")));

		// 添加附件
		File file = new File("D:\\1.txt");
		FileBody attachment = new FileBody(file, "application/octet-stream", "UTF-8");
		entity.addPart("attachments", attachment);

		// 添加附件, 文件流形式
		// File file = new File("D:\\1.txt");
		// String attachName = "attach.txt";
		// InputStreamBody is = new InputStreamBody(new FileInputStream(file),
		// attachName);
		// entity.addPart("attachments", is);

		httpost.setEntity(entity);

		HttpResponse response = httpclient.execute(httpost);
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
		send_common();
		// send_common_with_attachment();
		// send_template();
		// send_template_maillist();
		// send_template_with_attachment();

	}
}
