package sendcloud.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class VoiceSend44 {

	public static void main(String[] args) {

		 String url = "http://www.sendcloud.net/smsapi/sendVoice";
         String smsKey = "***";

         // 填充参数
         Map<String, String> params = new HashMap<String, String>();
         params.put("smsUser", "***");
         params.put("phone", "13412345678");
         params.put("code", "123456");

         // 对参数进行排序
         Map<String, String> sortedMap = new TreeMap<String, String>(new Comparator<String>() {
             public int compare(String arg0, String arg1) {
                 // 忽略大小写
                 return arg0.compareToIgnoreCase(arg1); 
             }
         });
         sortedMap.putAll(params);

         // 计算签名
         StringBuilder sb = new StringBuilder();
         sb.append(smsKey).append("&");
         for (String s : sortedMap.keySet()) {
             sb.append(String.format("%s=%s&", s, sortedMap.get(s)));
         }
         sb.append(smsKey);
         String sig = DigestUtils.md5Hex(sb.toString());

         // 将所有参数和签名添加到post请求参数数组里
         List<NameValuePair> postparams = new ArrayList<NameValuePair>();
         for (String s : sortedMap.keySet()) {
             postparams.add(new BasicNameValuePair(s, sortedMap.get(s)));
         }
         postparams.add(new BasicNameValuePair("signature", sig));

         HttpPost httpPost = new HttpPost(url);
         try {
             httpPost.setEntity(new UrlEncodedFormEntity(postparams, "utf8"));
             CloseableHttpClient httpClient;
             RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(100000).build();
             httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
             HttpResponse response = httpClient.execute(httpPost);
             HttpEntity entity = response.getEntity();
             System.out.println(EntityUtils.toString(entity));
             EntityUtils.consume(entity);
         } catch (Exception e) {
             System.out.println(e.toString());
         } finally {
             httpPost.releaseConnection();
         }
	 }

}
