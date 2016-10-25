- - - 
    
## 短信类型


目前 SendCloud 的短信服务支持验证码, 行业通知和营销短信.
    
**验证码短信**：注册密码, 修改密码, 身份有验证等.
    
**行业通知短信**：由用户行为触发生产的通知, 如:订单通知, 回复通知等.
    
**营销短信**: 向用户统一发送的消息通知等.

- - -  
## 接入方式    
     
目前 SendCloud 对短信业务提供 WEBAPI 的接入方式, 开发者可以利用 SendCloud 提供的 HTTP 接口, 调用 SendCloud 的服务. 

使用我们的短信API, 开发者需要创建:

* SMS_USER 
* SMS_KEY
* 短信模板
* 签名
    
** SMS_USER 与 SMS_KEY **

用户可以通过`【短信页面】-【发送设置】-【发送授权】`来创建 SMS_USER, 同时 SendCloud 会自动生成对应的 SMS_KEY. 目前 SendCloud 只允许拥有一个 SMS_USER.

**短信模板**
    
短信模板, 用户在发送短信之前,必须在前台页面编辑短信内容, 并提交审核. 审核通过后, 通过模板的 ID 调用发送. 

**变量替换**

SendCloud 支持在短信中使用「变量」.

变量的格式: 首尾使用 `%` 包围, 即为用户定义的一个变量. 举例:

```
欢迎使用爱发信. 您的手机验证码是: %code%. # code是变量
```

变量的用法:

1. 在短信模板中使用变量, 作为占位符
2. 在短信 API 中设置变量的值
3. SendCloud 会根据请求参数, 来替换短信内容中相应变量的值
4. 变量的值的长度不能超过 32 个字符, 变量的值中不能含有 HTTP 链接

变量的命名规则:

1. 变量名可包含字母（大小写均可）、数字、'_'(下划线)或'-'(中划线)的任意组合，不得出现其他字符
2. 变量名以字母(大小写均可)、数字、'_'(下划线)或'-'(中划线)开头
3. 变量名长度不得超过32个字符

**签名**
    
由于短信发送的特殊性, 以及相关的 ISP 机构的审查制度, 短信内容中必须含有能说明发送者身份的签名. 目前, SendCloud 支持在编辑短信模板时添加签名, 并设置签名位置(目前只支持签名在短信尾部).

`
注意:
短信模板必须含有「签名」, 否则不能通过审核. 目前用户只能拥有1个签名.
`

## 拦截列表

为减少用户对空号, 停机等手机号码的无效发送, SendCloud 会根据运营商反馈的发送结果来做相应的拦截处理.

所有```发送失败```的手机号码都会进入拦截列表, 拦截列表记录的字段如下: 

* 手机号码: 拦截的手机号码
* 拦截时间: 此时间之后的发送会被 SendCloud 拦截
* 失效时间: 此时间之后的发送恢复正常
* 原因: 运营商反馈的发送结果明细

不同的失败原因会产生不同的拦截时间, 不同的拦截范围, 详细见下表: 

|返回码|失败描述|拦截时间|全局,局部|用户是否可删除|
|:-----|:-------|:-------|:--------|:-------------|
|500|发送失败, 手机空号|30 天|全局拦截|是|
|510|发送失败, 手机停机|1 小时|全局拦截|是|
|550|发送失败, 该模板内容被投诉|1 小时|局部拦截|是|
|580|发送失败, 手机关机|0 秒||是| 
|590|发送失败, 其他原因|0 秒||是|

全局拦截: 此条拦截记录会对 SendCloud 所有用户生效. 

局部拦截: 此条拦截记录只会对来源用户生效.

用户可以在拦截列表中删除```来源是自己```的记录, 对于```来源不是自己```的拦截手机号, 可以在确认手机号通讯正常之后, 联系客服处理.

## API 验证机制

短信发送使用**数字签名**的验证模式. 这种模式能够有效避免密码在传输途中的泄露, 是安全级别很高的一种加密验证方式.

**数字签名**的验证模式: 调用 API 时, 用户不需要把密码 ( SMS_KEY ) 作为参数明文传输, 而是将数字签名 ( signature ) 作为参数传输给服务器. 服务器端会验证此 signature 的正确性.

生成**数字签名** ( signature ) 的方法: 
```
1. 将实际调用API的参数以字母升序(A-Z)排列, 不包括 smsKey 和 signature 字段本身
2. 按照排列之后的顺序, 以 'key=value' + '&' + 'key=value' 的方式连接所有参数, 
   得到字符串 param_str
3. 以 SMS_KEY + '&' + param_str + '&' + SMS_KEY 的方式得到字符串 sign_str
4. 计算 sign_str 的MD5值 (32位, 不区分大小写), 得到 signature

提示:
1. 生成签名时, 参数不要使用 'urlencode'. 在调用 api 时, 才需要对参数做 'urlencode'
2. '&' 是代码中使用的连接符, '+'是文档显示之用

```

下面提供了一个代码示例 ( python ):

```python
import hashlib

SMS_USER = 'testuser'
SMS_KEY = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'

param = {
    'smsUser': SMS_USER,
    'templateId' : 1,
    'phone'  : 18888888888,
    'vars'   : {},
}

param_keys = list(param.keys())
param_keys.sort()

param_str = ''

for key in param_keys:
    param_str += key + '=' + str(param[key]) + '&'

param_str = param_str[:-1]

sign_str = SMS_KEY + '&' + param_str + '&' + SMS_KEY
signature = hashlib.md5(sign_str).hexdigest()
```

**提示**: 所有的 API 都支持 HTTPS. 

**timestamp 时间戳 ( 提升逼格 )**

用户可以在每个 API 请求中加入 timestamp 参数, SendCloud 会检查 timestamp 和 服务器当前时间, 如果两者相差大于60秒, 则请求会被拒绝.

用户需要通过调用 API 来获取 SendCloud 服务器的时间戳, 而不是自己的本地时间.

timestamp 参数需要被包含在 signature 中, 参与生成数字签名.

- - -

## SMSHook

#### SMSHook 机制

用户将短信、语音请求发送给 SendCloud 之后, SendCloud 会把「请求结果」同步返回给用户, 而短信、语音的「发送结果」和 「其他事件结果」是通过 SMSHook 异步返回给用户的.

* SendCloud 为客户提供了一些事件, 客户可以选择关注某些事件
* 当某事件发生, 就会触发 SendCloud 向客户设置的 URL 发送数据 ( POST )
* 客户收到数据, 解析出事件和数据, 做后续的处理
       
目前 SendCloud 支持的事件如下:
     
|事件                   |触发条件                     |
|:----------------------|:----------------------------|
|请求(request)          |请求成功                     |
|送达(deliver)          |发送成功                     |
|处理失败(workererror)  |处理失败                     |
|发送失败(delivererror) |发送失败                     |
|回复(reply)            |用户回复（仅短信有）         |

使用方法:

* 用户自行编写 HTTP 服务, 使之能够处理相应的事件, 解析相关数据, 并开放出相应URL
* 用户在 SendCloud 的 `【短信语音】- 【发送设置】-【SMSHook】` 中选择关注的事件, 配置接收数据的 URL

`注意: 我们会对用户提供的 URL 做检测. 需要此 HTTP 服务能够正确响应 get | post 请求, 并且保证返回的 HTTP 状态码 为 200`
   
#### 签名验证

为了确保消息的来源身份是 SendCloud,  你可以选择对 POST 数据的来源进行安全认证. ( 不验证, 直接解析 POST 的数据也可以 ). 

安全认证的方法如下:
     
* 通过`【短信语音】- 【发送设置】-【SMSHook】`获取 `APP KEY`
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

**php 代码示例**
```
function verify($appkey,$token,$timestamp,$signature){
        $hash="sha256";
            $result=hash_hmac($hash,$timestamp.$token,$appkey);
                return strcmp($result,$signature)==0?1:0;
}
```

#### 事件说明
    
目前 SMSHook 支持的事件类型包括: 请求, 送达, 处理失败, 发送失败, 用户回复.

** 请求 ( request )**

参数说明

|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"request"|
|eventType|int|事件类型代码:1|
|message|string|消息内容|
|smsUser|string|smsUser|
|smsIds|list|短信(或语音)ID组成的数组|
|templateId|int|模板ID|
|phones|list|手机号组成的数组|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
|userId|int|用户ID|
|labelId|int|预留, 暂不用|
    
POST 数据示例

```
signature: 9eb24a034b655257a63c209b35b64b2a4ec5e894ed513c0410a3a6dc570aaaaa
event: request
userId: 19999
timestamp: 1434684323193
eventType: 1
templateId: 29999
message: request
smsUser: smsuser
phones: ["13888888888"]
token: nyFltYEluRVvYezFHJW1st2ewb71RVcVDiNN6GqvRnWtgDDDDD
smsIds: ["1434684322919_95_1_1_9m9684$13888888888"]
labelId: 0
```

** 送达 ( deliver )**

参数说明

|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"deliver"|
|eventType|int|事件类型代码:2|
|message|string|消息内容|
|smsUser|string|smsUser|
|smsId|int|短信(或语音)ID|
|templateId|int|模板ID|
|phone|string|手机号|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
|userId|int|用户ID|
|labelId|int|预留, 暂不用|
    
POST 数据示例

```
signature: e784e44949a403e952813bb7bb97d3fce422bbc2a2e2d90be7bba7b1689aaaaa
phone: 13888888888
event: deliver
userId: 19999
timestamp: 1434684324073
smsUser: smsuser 
templateId: 29999
message: Successfully delivered
eventType: 2
token: uBHSaB9Jj7jN7VN05u11jXuDZT4KIvfMnfrHlIxOOekwUaaaaa
smsId: 1434684322919_95_1_1_9m9684$13888888888
labelId: 0
```

** 处理失败 ( workererror)**

参数说明

|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"workererror"|
|eventType|int|事件类型代码:4|
|message|string|消息内容|
|encodeMessage|string|base64编码的消息内容|
|statusCode|int|错误码|
|smsUser|string|smsUser|
|smsId|int|短信(或语音)ID|
|templateId|int|模板ID|
|phone|string|手机号|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
|userId|int|用户ID|
|labelId|int|预留, 暂不用|
    
POST 数据示例

```
event: workererror
eventType: 4
token: O9CDUhNXxw7y23hMCLSpveIS6VTDF7McFr0EMF0XuJleTAAAAA
signature: cddc66d39d357feae4993ea224eda165e13190640af1a004d2fb67be5a1aaaaa
message: smsworker:address in unsubscribe list
encodeMessage: c21zd29ya2VyOmFkZHJlc3MgaW4gdW5zdWJzY3JpYmUgbGlzdA==
userId: 19999
smsUser: smsuser
templateId: 29999
statusCode: 430
smsId: 1434684949823_95_1_1_x3rfya$13888888888
phone: 13888888888
timestamp: 1434684950692
labelId: 0
```

** 发送失败 ( delivererror)**

参数说明

|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"delivererror"|
|eventType|int|事件类型代码:5|
|message|string|消息内容|
|encodeMessage|string|base64编码的消息内容|
|statusCode|int|错误码|
|smsUser|string|smsUser|
|smsId|int|短信(或语音)ID|
|templateId|int|模板ID|
|phone|string|手机号|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
|userId|int|用户ID|
|labelId|int|预留, 暂不用|
    
POST 数据示例

```
event: delivererror
eventType: 5
token: ZqozWAlTLjosjb3yrCDxBttAQyfAdBFo5PxrhF5iGkqbCAAAAA
signature: 16699442298213ccf099b2b80a938e3cb7ec0e3c153b2fc185017c9958eaaaaa
message: 12
encodeMessage: MTI=
userId: 19999
smsUser: smsuser
statusCode: 500
smsId: 1434685825229_95_1_1_o9amg7$13888888888
phone: 13888888888
timestamp: 1434685829536
labelId: 0
templateId: 29999
```
    
** 回复 (reply)** 
    
|参数|类型|说明|
|:---|:---|:---|
|event|string|事件类型:"reply"|
|eventType|int|事件类型代码:6|
|smsUser|string|smsUser|
|templateId|int|模板ID|
|phone|string|手机号|
|timestamp|long|时间戳|
|token|string|随机产生的长度为50的字符串|
|signature|string|签名字符串|
|userId|int|用户ID|
|labelId|int|预留, 暂不用|
|replyContent|string|回复内容|
|encodeReplyContent|string|base64编码的回复内容|
|replyTime|Datetime|回复时间|
    
POST 数据示例
    
```
token: bNmTl6jfT0nmX8dnRPiZZzPBrtsnkhrVjd1SZTPyx1UhtbEXvG
timestamp: 1455685829536
labelId: 0
phone: 13888888888
replyContent: test_reply
encodeReplyContent: dGVzdF9yZXBseQ==
replyTime: 2015-07-16 16:16:16
userId: 19999
templateId: 0
smsUser: smsuser
event: reply
signature: 5ea667531adacf550c30fdc07225b04fd7f44fce23fe806a561c67150a17b20c
eventType: 6
```
    
- - - 

