- - - 
    
## 短信类型

目前 SendCloud 的短信服务只支持触发短信
    
**触发短信**：由用户行为触发生产, 如:动态密码, 手机验证, 订单通知等.

- - -  
## 接入方式    
     
目前 SendCloud 对短信业务提供 WEBAPI 的接入方式, 开发者可以利用 SendCloud 提供的 HTTP 接口, 调用 SendCloud 的服务. 

使用我们的短信API, 开发者需要创建:

* SMS_USER 
* SMS_KEY
* 短信模板
* 签名
    
** SMS_USER 与 SMS_KEY **

用户可以通过`【短信页面】-【短信设置】-【发送授权】`来创建 SMS_USER, 同时 SendCloud 会自动生成对应的 SMS_KEY. 目前 SendCloud 只允许拥有一个 SMS_USER.

**短信模板**
    
短信模板, 用户在发送短信之前,必须在前台页面编辑短信内容, 并提交审核. 审核通过后, 通过模板的 ID 调用发送. 
    
**签名**
    
由于短信发送的特殊性, 以及相关的 ISP 机构的审查制度, 短信内容中必须含有能说明发送者身份的签名. 目前, SendCloud 支持在编辑短信模板时添加签名, 并设置签名位置(目前只支持签名在短信尾部).

`
注意:
短信模板必须含有「签名」, 否则不能通过审核. 目前用户只能拥有1个签名.
`
- - -

## API 验证机制

短信发送使用**数字签名**的验证模式. 这种模式能够有效避免密码在传输途中的泄露, 是安全级别很高的一种加密验证方式.

**数字签名**的验证模式: 调用 API 时, 用户不需要把密码 ( SMS_KEY ) 作为参数明文传输, 而是将数字签名 ( signature ) 作为参数传输给服务器. 服务器端会验证此 signature 的正确性.

生成**数字签名** ( signature ) 的方法: 
```
1. 将实际调用API的参数以字母升序(A-Z)排列, 不包括 smsKey 和 signature 字段本身
2. 按照排列之后的顺序, 以 'key=value' + '&' + 'key=value' 的方式连接所有参数, 
   得到字符串 param_str
3. 以 SMS_KEY + '&' + param_str + '&' + SMS_KEY 的方式得到字符串 sign_str
4. 计算 sign_str 的MD5值, 得到 signature

'&' 是代码中使用的连接符, '+'是文档显示之用
```

下面提供了一个代码示例 ( python ):

```python
import hashlib

SMS_USER = 'testuser'
SMS_KEY = 'A16a9yjNLS4DiasxcfqQRG4WOgdx0r6C'

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

用户可以在每个 API 请求中加入 timestamp 参数, SendCloud 会检查 timestamp 和 服务器当前时间, 如果两者相差大于6秒, 则请求会被拒绝.

用户需要通过调用 API 来获取 SendCloud 服务器的时间戳, 而不是自己的本地时间.

timestamp 参数需要被包含在 signature 中, 参与生成数字签名.


