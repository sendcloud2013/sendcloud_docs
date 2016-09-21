### Python

```
import requests
import json
import urllib
import time
from hashlib import md5

def generate_md5(fp):
    m = md5()    
    m.update(fp)
    return m.hexdigest()

def sendx():
    url = 'http://sendcloud.sohu.com/smsapi/sendx'
    SMS_USER = '***'
    SMS_KEY = '***'

    tos = [{'phone':'13488888888', 'vars':{"%reason%":"r1"}}, {'phone':'13488888889', 'vars':{"%reason%":"r2"}}]

    param = {
        'smsUser': SMS_USER,
        'templateId' : 734,
        'msgType': 0,
        'tos': str(tos)
    }

    param_keys = list(param.keys())
    param_keys.sort()

    param_str = ""
    for key in param_keys:
        param_str += key + '=' + str(param[key]) + '&'
    param_str = param_str[:-1]

    sign_str = SMS_KEY + '&' + param_str + '&' + SMS_KEY
    sign = generate_md5(sign_str)

    param['signature'] = sign

    print param
    res = requests.post(url,data=param)
    print res.text
   
def send():
    url = 'http://sendcloud.sohu.com/smsapi/send'
    SMS_USER = '***'
    SMS_KEY = '***'

    param = {
        'smsUser': SMS_USER,
        'templateId' : 381,
        'msgType': 0,
        'phone' : 13488888888,
        'vars' : '{"%content%":"liubidatest"}'
    }

    param_keys = list(param.keys())
    param_keys.sort()

    param_str = ""
    for key in param_keys:
        param_str += key + '=' + str(param[key]) + '&'
    param_str = param_str[:-1]

    sign_str = SMS_KEY + '&' + param_str + '&' + SMS_KEY
    sign = generate_md5(sign_str)

    param['signature'] = sign

    res = requests.post(url,data=param)
    print res.text

if __name__ == '__main__':
    #sendx()
    send()

```

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
```
[httpclient](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.4)
[httpmime](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime/4.4)

```
public class SmsApi_Send {

    public static void main(String[] args) {

        String url = "http://sendcloud.sohu.com/smsapi/send";
        String smsKey = "***";
        
        // 填充参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("smsUser", "***");
        params.put("templateId", "1");
        params.put("msgType", "0");
        params.put("phone", "13412345678");
        params.put("vars", "{\"code\":\"123455\"}");
        
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

```
```
public class SmsApi_SendX {

    public static void main(String[] args) {

        String url = "http://sendcloud.sohu.com/smsapi/sendx";
        String smsKey = "***";
        
        // 填充参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("smsUser", "***");
        params.put("templateId", "1");
        params.put("msgType", "0");
        params.put("tos", "[{\"phone\":\"13412345678\",\"vars\":{\"%code%\": \"121212\"}}]");
        
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
```

代码示例需要依赖如下jar包(httpcompoments4.2)
```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.2</version>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpmime</artifactId>
    <version>4.2</version>
</dependency>
```
[SMSAPI_4.2 Send代码示例](sms/downloads/java/SmsApi_42.java)

[SMSAPI_4.2 SendX代码示例](sms/downloads/java/SmsApiSendX_42.java)

### PHP

```
<?php

function send_sms() {
        $url = 'http://sendcloud.sohu.com/smsapi/send';

        $param = array(
            'smsUser' => '***', 
            'templateId' => '1',
            'msgType' => '0',
            'phone' => '13412345678',
            'vars' => '{"%code%":"123456"}'
        );

        $sParamStr = "";
        ksort($param);
        foreach ($param as $sKey => $sValue) {
            $sParamStr .= $sKey . '=' . $sValue . '&';
        }
        
        $sParamStr = trim($sParamStr, '&');
        $smskey = '***';
        $sSignature = md5($smskey."&".$sParamStr."&".$smskey);


        $param = array(
            'smsUser' => '***', 
            'templateId' => '1',
            'msgType' => '0',
            'phone' => '13412345678',
            'vars' => '{"%code%":"123456"}',
            'signature' => $sSignature
        );

        $data = http_build_query($param);
        echo $data;

        $options = array(
            'http' => array(
                'method' => 'POST',
                'header' => 'Content-Type:application/x-www-form-urlencoded',
                'content' => $data

        ));
        $context  = stream_context_create($options);
        $result = file_get_contents($url, FILE_TEXT, $context);

        return $result;
}

echo send_sms();
?>
```

### Ruby
    
```
#!/usr/bin/env ruby

require 'rubygems'
require 'digest'
require 'rest_client'

def sorted_hash(aHash)
   return aHash.sort{|a,b| a.to_s <=> b.to_s}
end

def send_mail
        param = {
            "smsUser"=> "***", 
            "templateId"=> "11",
            "msgType" => "0",
            "phone"=> '13412345678',
            "vars"=>'{"%code%":"123456"}'  
        }
        api_key = "***"
        paramstr = ""
        paramstr << api_key << "&"
        new_param = sorted_hash(param)
        new_param.each do |item|
            key = item[0]
            value = item[1]
            paramstr << key << "=" << value << "&"
        end
        paramstr << api_key
        
        md5 = Digest::MD5.new
        sign = md5.update(paramstr)
        
        response = RestClient.post "http://sendcloud.sohu.com/smsapi/send?",
            :smsUser => "***",
            :templateId => "11",
            :msgType => '0',
            :phone => '13412345678',
            :vars =>'{"%code%":"123456"}',
            :signature => sign

return response
end

response = send_mail
puts response.code
puts response.to_str

```

### Perl
    
```
use strict;
use LWP::UserAgent;
use HTTP::Request::Common;
use URI::Escape;
use Digest::MD5;

my $uri = 'http://sendcloud.sohu.com/smsapi/send';
my $ua = LWP::UserAgent->new;
my %param = (
    "smsUser" => '***',
    "templateId" => '1',
    "msgType" => '0',
    "phone" => '13412345678', 
    "vars" => '{"%code%":"123456"}');
my $api_key = "***";
my $param_str = $api_key;

foreach my $item (sort keys %param){
    $param_str .= ("&".$item."=".$param{$item});
}
$param_str .= ("&".$api_key);

my $md5 = Digest::MD5->new;
$md5->add($param_str);
my $sign = $md5->hexdigest;

my $request = POST $uri,
    Content => [
        smsUser => '***', 
        templateId => '1',
        msgType => '0',
        phone => '13412345678', 
        vars => '{"%code%":"123456"}',
        signature => $sign
    ],
    Content_Type => 'application/x-www-form-urlencoded';

my $response = $ua->request($request) ;

if ($response->is_success()) {
    print $response->content, "\n";
} else {
    print $response->as_string, "\n";
}

exit;

```

### CSharp

```

System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using System.Security.Cryptography;

namespace SendCloud
{
    class SendCloudSMS
    {

        public static String generate_md5(String str) {
            MD5 md5 = new MD5CryptoServiceProvider();

            //compute hash from the bytes of text
            md5.ComputeHash(Encoding.GetEncoding("utf-8").GetBytes(str));

            //get hash result after compute it
            byte[] result = md5.Hash;

            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < result.Length; i++)
            {
                strBuilder.Append(result[i].ToString("x2"));
            }

            return strBuilder.ToString();
        }

        public static void send()
        {
            String url = "http://sendcloud.sohu.com/smsapi/send";

            String smsUser = "";
            String smsKey = "";

            List<KeyValuePair<String, String>> paramList = new List<KeyValuePair<String, String>>();
            paramList.Add(new KeyValuePair<String, String>("smsUser", smsUser));
            paramList.Add(new KeyValuePair<String, String>("templateId", "732"));
            paramList.Add(new KeyValuePair<String, String>("phone", "13488888888"));
            paramList.Add(new KeyValuePair<String, String>("msgType", "0"));
            paramList.Add(new KeyValuePair<String, String>("vars", "{\"%submit_date%\":\"20160316\"}"));

            paramList.Sort(
                delegate(KeyValuePair<String, String> p1, KeyValuePair<String, String> p2)
                {
                    return p1.Key.CompareTo(p2.Key);
                }
            );

            var param_str = "";
            foreach (var param in paramList)
            {
                param_str += param.Key.ToString() + "=" + param.Value.ToString() + "&";
            }

            String sign_str = smsKey + "&" + param_str + smsKey;
            String sign = generate_md5(sign_str);

            paramList.Add(new KeyValuePair<String, String>("signature", sign));

            HttpClient client = null;
            HttpResponseMessage response = null;

            try
            {
                client = new HttpClient();
                response = client.PostAsync(url, new FormUrlEncodedContent(paramList)).Result;
                String result = response.Content.ReadAsStringAsync().Result;
                Console.WriteLine("result:{0}", result);
            }
            catch (Exception e)
            {
                Console.WriteLine("\nException Caught!");
                Console.WriteLine("Message :{0} ", e.Message);
            }
            finally
            {
                if (null != response)
                {
                    response.Dispose();
                }
                if (null != client)
                {
                    client.Dispose();
                }
            }
        }

        static void Main(string[] args)
        {
            send();
            Console.ReadKey();
        }
    }
}

```
    
### Nodejs
    
```
var http = require("http")
var crypto = require('crypto')

function md5(data) {
    var Buffer = require("buffer").Buffer;
    var buf = new Buffer(data);
	var str = buf.toString("binary");
	var crypto = require("crypto");
	return crypto.createHash("md5").update(str).digest("hex");
} 

function sortDict(dict){
    var dict2={},
        keys = Object.keys(dict).sort();
    for (var i = 0, n = keys.length, key; i < n; ++i) {
        key = keys[i];
        console.log(key);
        dict2[key] = dict[key];
    }
    return dict2;
}

var smsKey = '***';

var param = {
    'msgType':0,
    'smsUser':'***',
    'templateId' : 1,  
    'phone' : 13112345678, 
    'vars' : '{%code%:123456}'
}

sorted_param = sortDict(param);

var param_str = "";
for(var key in sorted_param)
    param_str += (key + '=' + sorted_param[key] + '&')
var param_str = smsKey + '&' + param_str + smsKey;
var sign = md5(param_str);
param['signature'] = sign.toUpperCase();


data = require('querystring').stringify(param); 
var options = {
    host:"sendcloud.sohu.com",
    port:80,
    path:"/smsapi/send",
    method:"POST"
}
options.path = options.path + '?' + data;
console.log(options.path);

var req = http.request(options, function(res){
    var responseStr = '';
    res.on('data', function (chunk) {
        responseStr += chunk;
    });
    res.on('end', function() {
        console.log(responseStr);
    });
});
req.end();

```
