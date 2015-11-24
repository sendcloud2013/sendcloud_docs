
## 邮件追踪 (track)

邮件追踪能够帮助用户收集已发出邮件的「打开数据」,「点击数据」,「退订数据」, 以此来评估邮件发送的效果.

你可以通过`【设置】-【追踪选项】`来配置某个「API_USER」的「打开追踪」,「点击追踪」,「订阅追踪」.

注意: 「点击追踪」依赖于用户发信域名的[CNAME 配置](../guide/base.md#_3)
    
- - -
    
## 地址列表

地址列表是一个方便用户发送邮件的功能. 用户编辑/导入地址列表后, 在请求接口时, 不需要再传输邮件地址, 只需在参数中指定地址列表的「别称地址」即可.

地址列表中可以设置「变量的值」, 用于替换「邮件内容」中的「变量」.

用户可以通过`【发送相关】-【地址列表】`来创建【地址列表】.

注意: 

1. 只有通过 WEBAPI 的方式发送邮件, 才支持使用地址列表. SMTP 接入方式暂不支持.
2. 只有使用 「批量类型」的 API_USER 调用接口, 才支持使用地址列表
3. 只有付费用户才支持使用地址列表
     
- - -
    
## 标签 (label)

标签能够帮助用户对邮件进行分类, 适用于 A/B 测试等场景.

> 给2种风格的邮件赋予不同的标签进行测试发送, 通过标签分别查看2种风格邮件的打开/点击数据
> 就能知道接收者最喜欢哪种邮件了~

注意: 每封邮件只能使用一个标签

- - -
    
## 收信路由 

如果你能收到你的用户的回信, 那真是一件让人激动的事情, 所以, SendCloud 为你准备了这个贴心的功能.

此功能就是把用户回复到某地址的邮件转发给你设置的邮箱, 或是你设置的某个URL. 你可以接收邮件数据, 再做后续处理.

当然, 直接发给这个地址的邮件也会被转发.

![pic](/resources/mx.jpg)

用户可以通过某个域名配置里面的`【收信配置】-【收信路由】`来创建收信路由.
     
- - - 
   
## 订阅关系

订阅关系的作用是帮助用户创建, 维护和用户的订阅关系, 帮助用户快速搭建自己的邮件订阅系统. 

SendCloud 和 ESP 都在推进建立用户和用户订阅关系, 也会大力支持这种存在订阅关系的邮件发送 ( 比如 QQ邮件列表 ).  
        
1. 客户在 SendCloud 中配置生成一个「订阅关系」: 
![pic](/resources/subscribe_dingyue.png)
        
2. 客户将生成的 JS 代码放置在自己的网站中
         
3. 用户访问客户的网站时, 输入邮箱地址, 订阅某条信息  
         
4. 用户的邮箱地址自动加入客户的某个「地址列表」. 
         
5. 用户收到确认邮件, 确认订阅操作. 
         
6. 这样, 用户就完成了和客户网站的订阅关系.    
         
此功能依赖于「地址列表」, 客户在配置订阅关系时需要将此订阅关系绑定到某个「地址列表」.
    
- - -

## 取消订阅样式
    
在开启「订阅追踪」后, 系统会在邮件中默认自动加上 **取消订阅** 的退订链接, 供收件人退订此类邮件.

下面是系统默认的取消订阅样式:
![pic](/resources/default_unsubscribe.png)

你可以自行定义取消订阅的样式, 只要在 href 中插入 SendCloud 内部变量 `%%user_defined_unsubscribe_link%%` 即可. 示例如下：

**使用默认的取消订阅样式, 邮件无需特别处理, 内容如下:**
```
<p>亲爱的%name%:</p>
<p style="margin-bottom: 35px">您好! 您本月在爱发信的消费金额为: %money% 元.</p>
```
邮件内容效果截图如下:
![pic](/resources/unsubscribe_1.png)

- - -

**使用自定义的取消订阅样式, 邮件内容如下:**
```
<p>亲爱的%name%:</p>
<p style="margin-bottom: 35px">您好! 您本月在爱发信的消费金额为: %money% 元.</p>
<p><a style="background: #1ABC9C;border:1px solid #13A386;padding:8px 20px;color: #fff;text-decoration:none;border-radius:4px" href="%%user_defined_unsubscribe_link%%">不想再收到此类邮件</a></p>
```
邮件内容效果截图如下:
![pic](/resources/unsubscribe_2.png)

- - -
    
## WebHook

用户将邮件请求发送给 SendCloud 之后, SendCloud 会把「请求结果」同步返回给用户, 而邮件的「发送结果」和「其他事件结果」是通过 WebHook 异步返回给用户的.

**WebHook 机制:**

* SendCloud 为客户提供了一些邮件事件, 客户可以选择关注某些事件
* 当某事件发生, 就会触发 SendCloud 向客户设置的 URL 发送数据 ( POST )
* 客户收到数据, 解析出事件和数据, 做后续的处理
       
目前 SendCloud 支持的邮件事件如下:
     
|事件                 |触发条件         |
|:--------------------|:--------------- |
|请求(request)        |邮件请求成功     |
|发送(deliver)        |邮件发送成功     |
|打开(open)           |用户打开邮件     |
|点击(click)          |用户点击链接     |
|取消订阅(unsubscribe)|用户取消订阅邮件 |
|软退信(bounce)       |邮件被 ESP 接收之后, 又被退回|
|举报(report_spam)    |用户举报邮件     |
|无效邮件(invalid)    |邮件未发送成功   |

**使用方法:**

* 用户自行编写 HTTP 服务, 使之能够处理相应的事件, 解析相关数据, 并开放出相应 URL
* 用户在 SendCloud 的 `【邮件】- 【邮件设置】-【WebHook】` 中选择关注的事件, 配置接收数据的 URL

`注意: 我们会对用户提供的 URL 做检测. 需要此 HTTP 服务能够正确响应 get | post 请求, 并且保证返回的 HTTP 状态码 为 200`

**签名验证**

为了确保消息的来源身份是 SendCloud,  你可以选择对 POST 数据的来源进行安全认证. ( 不验证, 直接解析 POST 的数据也可以 ). 

安全认证的方法如下:
     
* 通过`【设置】-【WebHook】`获取 `APP KEY`
* 解析出 POST 数据中的 `token`, `timestamp` 和 `signature`
* 使用 `APP KEY`, `token` 和 `timestamp` 生成签名 `signature`, 与 POST 数据中的 `signature` 进行校验 ( 签名算法: [SHA256](http://en.wikipedia.org/wiki/SHA-2))


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
    
**事件说明**
    
目前 WebHook 支持的事件类型包括: 请求, 发送, 打开, 点击, 取消订阅, 软退信, 举报, 无效邮件.

##### 请求事件 ( request )

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

##### 发送 ( deliver )
    
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

##### 打开 ( open )
    
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

##### 点击 ( click )
    
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

##### 取消订阅 ( unsubscribe )
    
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

##### 软退信 ( bounce )
    
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
    
##### 垃圾举报 ( report_spam )
    
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

##### 无效邮件 ( invalid )
    
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

**快速体验**

如果您目前没有可接收数据的 `url`, 您可以使用 [requestb.in](http://requestb.in/) 或者 [request 纷云版](http://request.lesschat.com/) 提供的服务来体验下 WebHook.

1. 点击 `Create a RequestBin`, 生成一个 `url`
2. 在 SendCloud 中配置此 `url` 用以接收 `WebHook` 的事件数据
3. 执行某种操作 (请求, 发送, 打开) 之后, 就可以在 `requestb.in` 的相关页面里看到此事件所 POST 的数据


