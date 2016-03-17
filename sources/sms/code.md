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
    send()

```

### Java

代码示例需要依赖如下jar包
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
[httpclient](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient/4.2)
[httpmime](http://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime/4.2)

```
public class SmsApi {

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
            @Override
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
            DefaultHttpClient httpclient = new DefaultHttpClient();
            httpclient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
            httpclient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 100000);
            HttpResponse response = httpclient.execute(httpPost);

            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            httpPost.releaseConnection();
        }
    }
}

```

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
            md5.ComputeHash(ASCIIEncoding.ASCII.GetBytes(str));

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

