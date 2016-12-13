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

public class SmsDataStatistics44 {

	public static String smsUser="***";
	public static String smsKey="***";
	
	public static String sendDay(){
		String url="http://www.sendcloud.net/smsapi/data/sendDay";
		//String days="30";
		String startDate="2016-09-01";
		String endDate="2016-09-30";
		//String templateIdsStr="34954";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("smsUser", smsUser);
		//params.put("days", days);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		//params.put("templateIdsStr", templateIdsStr);
		//params.put("msgTypeStr", "0");
		params.put("Aggregate", "1");
		
		
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
        System.out.println("sig="+sig);
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
		
		return null;
	}
	
	
	public static void processFailed(){
		
		String url="http://www.sendcloud.net/smsapi/data/processFailed";
		String days="100";
		//String templateIdsStr="2483";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("smsUser", smsUser);
		params.put("days", days);
		//params.put("templateIdsStr", templateIdsStr);
		//params.put("msgTypeStr", "1");
		params.put("aggregate", "1");
		
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
        System.out.println(sig);
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
	
	public static void sendFailed(){
		String url="http://www.sendcloud.net/smsapi/data/sendFailed";
		String days="90";
		String templateIdsStr="24";
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("smsUser", smsUser);
		params.put("days", days);
		//params.put("templateIdsStr", templateIdsStr);
		//params.put("msgTypeStr", "1");
		params.put("aggregate", "1");
		
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
        System.out.println(sig);
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
	
	public static void main(String[] args) {
		sendDay();
		processFailed();
		sendFailed();
	}
	
}
