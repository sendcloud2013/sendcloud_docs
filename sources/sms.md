# 短信发送

- - -
##短信类型    
    
目前 SendCloud 的短信服务只支持触发短信
    
**触发短信**：由用户行为触发生产, 如:动态密码, 手机验证, 订单通知等.


- - -  
## 接入方式    
     
目前 SendCloud 对短信业务提供 WEBAPI 的接入方式, 开发者可以利用 SendCloud 提供的 HTTP 接口, 调用 SendCloud 的服务. 

使用我们的短信API, 开发者需要创建:

* 子账号 SMS_USER 
* 对应的密码 SMS_KEY
* 短信模板
* 签名
    
** SMS_USER ( 子账号 ) 与 SMS_KEY ( 密码 ) **

用户可以通过`【短信页面】-【设置】-【发送授权】`来创建 SMS_USER, 同时 SendCloud 会自动生成对应的 SMS_KEY. 目前 SendCloud 只允许拥有一个 SMS_USER.

**短信模板**
    
短信模板, 将邮件发送中的样本和模板两个功能合二为一, 用户在发送短信之前,必须在前台页面编辑短信内容, 并提交审核. 审核通过后, 通过模板的 ID 调用发送. 
    
**签名**
    
由于短信发送的特殊性, 以及相关的 ISP 机构的审查制度, 短信内容中必须含有能说明发送者身份的签名. 目前, SendCloud 支持在编辑短信模板时添加签名, 并设置签名位置(信头或信尾).

注意: 没有「签名」的短信模板, 是不能通过审核的. 

- - -

##API
    
###send

用于一次发送一个短信模板给一个用户

**URL**
```
http://sendcloud.sohu.com/sms/send
```

**返回数据格式**
```
json
```

**HTTP 请求方式**    
```
POST    
```
    
**参数说明**
    
`send`
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|phone          |string         |是         |收信人手机号|
|vars           |string         |否         |替换变量的json串|
|timestamp      |string         |否         |UNIX时间戳|
|signature      |string         |是         |签名, 合法性验证|
    
    
`sendn`
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|tos            |string         |是         |手机号和替换变量的对应的json串|
|timestamp      |string         |否         |UNIX时间戳|
|signature      |string         |是         |签名, 合法性验证|
    
**URL**         

```
http://sms-api.apps.sohuno.com/sms/send
用于一次发送一个短信模板给一个用户

http://sms-api.apps.sohuno.com/sms/sendn 
用于一次发送一个短信模板给多个用户, 每个用户对应一个替换变量.
```

**支持格式**
```
json
```
**http 请求方式**    
```
POST    
```
    
**参数说明**
    
`send`
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|phone          |string         |是         |收信人手机号|
|vars           |string         |否         |替换变量的json串|
|timestamp      |string         |否         |UNIX时间戳|
|signature      |string         |是         |签名, 合法性验证|
    
    
`sendn`
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|tos            |string         |是         |手机号和替换变量的对应的json串|
|timestamp      |string         |否         |UNIX时间戳|
|signature      |string         |是         |签名, 合法性验证|
    

`smsKey不作为显示参数传递, 而是隐藏在signature中`    
    
*signature的生成方式如下:*
*将除开smsKey的所有参数按字母顺序排序, 并用&符号拼接, 然后将smsKey用&拼到头和尾得到一个字符串, 计算该字符串的MD5作为此次请求的signature.*
    
`过程有点复杂, 请大家看如下send中signature生成示例, sendn中同理`    
    
    
    smsUser = 'testuser'
    smsKey = 'A16a9yjNLS4DiasxcfqQRG4WOgdx0r6C'
    templateId = 1
    phone = 13811112222
    vars = {}
    timestamp = 1425456433050
    参数按字母排序为 phone smsUser templateId timestamp vars
    signature_str = 'A16a9yjNLS4DiasxcfqQRG4WOgdx0r6C&phone=13811112222&smsUser=testuser&templateId=1&timestamp=1425456433050&vars={}&A16a9yjNLS4DiasxcfqQRG4WOgdx0r6C'
    signature = calculate_md5(signature_string) 
              = ffc3451754fb2bce66ad3c586eb53fb2

    
*vars格式示例:*
    
    {"%name%": "lucy"}
    
*tos格式示例*
    
    [{"phone": "13111111111", "vars": {"%name%": "name1"}}, {"phone": "13122222222", "vars": {"%name%": "name2"}}]
    
    
    

- - - 
##API返回码

|HTTP状态码|含义|
|:---------|:---|
|200|发送成功|
|311|部分成功|
|401|短信内容不能为空|
|411|手机号不能为空|
|412|手机号格式错误|
|413|有重复的手机号|
|421|签名参数错误|
|422|签名错误|
|431|模板不存在|
|432|模板未提审或者未通过审核|
|433|模板ID不能为空|
|441|替换变量格式错误|
|461|时间戳无效, 与服务器时间相差太大|
|471|smsUser不存在|
|472|smsUser不能为空|
|473|没有权限|
|474|用户不存在|
|481|手机号和替换变量不能为空|
|482|手机号和替换变量格式错误|
|499|您的额度不够了|
|501|服务器异常|
    

    send, 返回一条信息的具体情况:
    {
        "message":"请求成功",
        "info":{},
        "result":true,
        "statusCode":200
    }
    sendn全部成功或全部失败返回与send相同
    部分成功则会返回具体失败信息
    {
        "message":"部分成功",
        "info":{
                "successCount":1,
                "failedCount":1,
                "items":[{"phone":"1312222","vars":{},"message":"手机号格式错误"}]
                },
        "result":true,
        "statusCode":311
    }
        




