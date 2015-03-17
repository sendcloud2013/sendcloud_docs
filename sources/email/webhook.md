## WebHook详细

### 签名验证

为了确保消息的来源身份是 SendCloud,  你可以选择对 POST 数据的来源进行安全认证. ( 不验证, 直接解析 POST 的数据也可以 ). 

安全认证的方法如下:
     
* 通过`【设置】-【WebHook】`获取 `APP KEY`
* 解析出 POST 数据中的 `token`, `timestamp` 和 `signature`
* 使用 `APP KEY`, `token` 和 `timestamp` 生成签名 `signature`, 与 POST 数据中的 `signature` 进行校验 ( 签名算法: [HMAC](http://en.wikipedia.org/wiki/Hash-based_message_authentication_code))


**python 代码示例**
```
import hashlib, hmac
def verify(appkey, token, timestamp, signature):
    return signature == hmac.new(
        key=appkey,
        msg='{}{}'.format(timestamp, token),
        digestmod=hashlib.sha256).hexdigest()
```  
    
**Java 代码示例** (依赖 [apache codec](http://commons.apache.org/proper/commons-codec/download_codec.cgi))
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
### 事件说明
    
目前 WebHook 支持的事件类型包括: 请求, 发送, 打开, 点击, 取消订阅, 软退信, 举报, 无效邮件.

#### 请求事件 ( request )

**参数说明**
     
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"request"|
|message|string|消息内容|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|messageId|string|messageId|
|category|string|发信子账号, 就是 API_USER|
|recipientArray|list|请求的收件人|
|emailIds|list|emailId 数组|
|labelId|int|自定义的标签ID|
|recipientSize|int|本次请求的个数|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
**POST 数据示例**

```
signature: d46a5c69c23b23a8270efa8b7fbb9c2d9230d30559e215a539909b432983a2f8
timestamp: 1426571113188
recipientArray: ["123@qq.com"]
emailIds: ["1426571113174_27372_24044_6376.sc-10_10_127_119-inbound0$123@qq.com"]
mail_list_task_id:
labelId: ***
category: ***
event: request
recipientSize: 1
message: successfully request
messageId: 1426571113174_27372_24044_6376.sc-10_10_127_119-inbound
token: iSXtPWbCNO5qiBrLhTRX48dbRujd3t0lL8RLg7ocJbhiDh6WxJ
```
- - - 
#### 发送 ( deliver )
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"deliver"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|labelId|int|自定义的标签ID|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
**POST 数据示例**

```
timestamp: 1426571118712
event: deliver
category: ***
labelId: *** 
emailId: 1426571113174_27372_24044_6376.sc-10_10_127_119-inbound0$123@qq.com
recipient: 123@qq.com
mail_list_task_id:
message: Successfully delivered
signature: ac1ebc61754fde9553196d0a4c8e987e2891e03fccb283fc2c49ff42ad34b21f
token: M1Q4BUFJRpQpjx9YIQvDz7ZCODPOYMHMKRLmS2Gd9rbxfcfGb8
```
- - -
#### 打开 ( open )
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"open"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
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
    
**POST 数据示例**

```
timestamp: 1426571228078
message: open email
oSName: Windows 7
recipient: 123@qq.com
event: open
category: ***
signature: 53bf46e97878f45c3191d697c9340a609ad3c1e27eab20133a6e2e7baf0fe1ce
emailId: 1426571113174_27372_24044_6376.sc-10_10_127_119-inbound0$123@qq.com
token: uBZHXO4o0qCJ1HkLPl7dXTb1ltglOCcmfqLQW4aBsoGp8HWcvv
explorerVer: 34.0.1847
labelId: ***
ip: ***
oSVer:
mail_list_task_id:
explorerName: Chromium
```
- - -
#### 点击 ( click )
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"click"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
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
    
**POST 数据示例**

```
timestamp: 1426571238869
message: click email
oSName: Windows 7 
recipient: 123@qq.com
event: click
category: ***
emailId: 1426571113174_27372_24044_6376.sc-10_10_127_119-inbound0$123@qq.com
signature: 9e27ab6c7eb4cffede24c721f7224b41df2e545526b894f5d83f9e57f26e790d
url: http://www.ifaxin.com
token: NxUUuxAzbgbm6Sx8k7GDRCJQOB5PrGNwBBSp8YLq4deNT18VLJ
explorerVer: 34.0.1847
labelId: ***
ip: ***
oSVer:
mail_list_task_id:
explorerName: Chromium
```

- - -
#### 取消订阅 ( unsubscribe )
    
**参数说明**
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"unsubscribe"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
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
    
**POST 数据示例**

```
timestamp: 1426571263755
message: unsubscribe email
oSName:
recipient: 123@qq.com
event: unsubscribe
category: ***
signature: 058c24197d3e2c9d179d8303c3290a22af237a7bbb2827bd546b5e3929c2b349
emailId: 1426571113174_27372_24044_6376.sc-10_10_127_119-inbound0$123@qq.com
token: DGoaUftgLIj3ePopdeB3y8Cm04ChhKWlr9m5ELHRfzH6lvQuxG
explorerVer:
labelId: ***
ip:
oSVer:
mail_list_task_id:
explorerName:
```
- - - 

#### 软退信 ( bounce )
    
**参数说明**    
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"bounce"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|reason|string|弹回原因|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
    
- - - 
#### 垃圾举报 ( report_spam )
    
**参数说明** 
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"report_spam"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用邮件类表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|

- - - 
#### 无效邮件 ( invalid )
    
**参数说明** 
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"invalid"|
|message|string|消息内容|
|category|string|发信子账号, 就是 API_USER|
|labelId|int|自定义的标签ID|
|mail_list_task_id|long|如果使用地址列表发送，将产生邮件列表任务id|
|emailId|string|每封email的唯一ID|
|recipient|string|收信人|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|

### mail_list_task_id_list

如果开发者使用地址列表来进行邮件发送, WEBAPI 会返回给用户一个 `mail_list_task_id_list`, 即获取一个 `task_id`.

在调用地址列表发送时, SendCloud 会认为此次调用是一次任务, 会将列表中的所有地址的发送都标记在这个 `task_id` 下.

当用户收到 WebHook POST 的数据时, 根据 `task_id` 和 `recipient` 就能唯一确定邮件请求, 收件人.


