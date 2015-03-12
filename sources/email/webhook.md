##WebHook签名验证
    
**python示例**
```
import hashlib, hmac
def verify(appkey, token, timestamp, signature):
    return signature == hmac.new(
        key=appkey,
        msg='{}{}'.format(timestamp, token),
        digestmod=hashlib.sha256).hexdigest()
```  
    
**java 示例(需要依赖[apache codec](http://commons.apache.org/proper/commons-codec/download_codec.cgi))**
```
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public boolean verify(String appkey, String token, long timestamp,
            String signature) throws NoSuchAlgorithmException, InvalidKeyException {
    Mac sha256HMAC = Mac.getInstance("HmacSHA256");
    SecretKeySpec secretKey = new SecretKeySpec(appkey.getBytes(),"HmacSHA256");
    sha256HMAC.init(secretKey);
    StringBuffer buf = new StringBuffer();
    buf.append(timestamp).append(token);
    String signatureCal = new String(Hex.encodeHex(sha256HMAC.doFinal(buf
            .toString().getBytes())));
    return signatureCal.equals(signature);
}
```
    
- - -
##事件参数
    
目前WebHook支持的事件类型包括:请求, 发送, 打开, 点击, 取消订阅, 软退信, 垃圾邮件举报, 无效邮件.

- - -
###请求
    
**参数说明**
     
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"request"|
|message|string|消息内容|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|messageId|string|邮件发送的请求ID|
|category|string|发信子账号|
|recipientArray|string|请求的收件人|
|labelId|int|自定义的标签ID|
|recipientSize|int|本次请求的个数|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - - 
###发送
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"deliver"|
|message|string|消息内容|
|category|string|发信子账号|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|labelId|int|自定义的标签ID|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - -
###打开
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"open"|
|message|string|消息内容|
|category|string|发信子账号|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|labelId|int|自定义的标签ID|
|ip|string|打开的Ip地址|
|explorerName|string|浏览器名称|
|explorerVer|string|浏览器版本|
|oSName|string|操作系统名称|
|oSVer|string|操作系统版本|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - -
###点击
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"click"|
|message|string|消息内容|
|category|string|发信子账号|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|labelId|int|自定义的标签ID|
|url|被点击的链接|
|ip|string|点击的Ip地址|
|explorerName|string|浏览器名称|
|explorerVer|string|浏览器版本|
|oSName|string|操作系统名称|
|oSVer|string|操作系统版本|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - -
###取消订阅
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"unsubscribe"|
|message|string|消息内容|
|category|string|发信子账号|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|labelId|int|自定义的标签ID|
|ip|string|Ip地址|
|explorerName|string|浏览器名称|
|explorerVer|string|浏览器版本|
|oSName|string|操作系统名称|
|oSVer|string|操作系统版本|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - - 
###软退信
    
**参数说明**    
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"bounce"|
|message|string|消息内容|
|category|string|发信子账号|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|reason|string|弹回原因|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - - 
###垃圾邮件事件
    
**参数说明** 
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"report_spam"|
|message|string|消息内容|
|category|string|发信子账号|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|






