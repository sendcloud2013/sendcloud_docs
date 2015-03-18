## send
     
发送一个短信模板给一个用户

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
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|phone          |string         |是         |收信人手机号|
|vars           |string         |否         |替换变量的json串|
|signature      |string         |是         |签名, 合法性验证|
|timestamp      |string         |否         |UNIX时间戳|
    
*vars格式示例:*

    {"%name%": "lucy"}

- - -
发送一个短信模板给多个用户, 每个用户对应一个替换变量.
    
## sendn
**URL**
```
http://sendcloud.sohu.com/sms/sendn
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
    
|参数           |类型           |必选       |说明|
|:--------------|:--------------|:----------|:---|
|smsUser        |string         |是         |子账号|
|templateId     |int            |是         |模板ID|
|tos            |string         |是         |手机号和替换变量的对应的json串|
|signature      |string         |是         |签名, 合法性验证|
|timestamp      |string         |否         |UNIX时间戳|

*tos格式示例:*
    
    [{"phone": "13111111111", "vars": {"%name%": "name1"}}, {"phone": "13122222222", "vars": {"%name%": "name2"}}]

- - -
    
## timestamp
     
获取服务器时间戳

**URL**
```
http://sendcloud.sohu.com/timestamp/get
```

**返回数据格式**
```
json
```

**HTTP 请求方式**    
```
GET
```
    
**参数说明**

无

    
