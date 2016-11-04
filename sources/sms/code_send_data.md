### Java

代码示例需要依赖如下jar包(httpcompoments4.4)
```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.4</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpmime</artifactId>
    <version>4.4</version>
</dependency>
<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.9</version>
</dependency>

```
[httpclient](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.4)
[httpmime](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime/4.4)
[commons-codec](https://mvnrepository.com/artifact/commons-codec/commons-codec)

```

public class smsSendData_44_demo {
	
	
	public static String smsUser="***";
	public static String smsKey="***";
	
    public static void sendDay(){
		String url="http://www.sendcloud.net/smsapi/data/sendDay";
		String days="10";
		//String startDate="2016-09-01";
		//String endDate="2016-11-01";
		String templateIdsStr="001";
		String msgTypeStr="0";
		String aggregate="1"
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("smsUser", smsUser);
		params.put("days", days);
		//params.put("startDate", startDate);
		//params.put("endDate", endDate);
		params.put("templateIdsStr", templateIdsStr);
		params.put("msgTypeStr", msgTypeStr);
		params.put("aggregate", aggregate);
		
		
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
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		sendDay();
	}

}

```
