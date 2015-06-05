## send
     
发送一个短信模板给一个用户

**URL**
```
http://sendcloud.sohu.com/smsapi/send
```

**返回数据格式**
```
json
```

**HTTP请求方式**    
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

    {"name": "lucy"} or {"%name%": "lucy"}

`注意`: 

1. 参数 vars 可能含有特殊字符, 记得 `urlencode`

2. vars 所传递的变量的值, 长度不能超过 32 个字符

3. HTTP 设置头域 `Content-Type: application/x-www-form-urlencoded`

4. 生成签名时, 参数不要使用 `urlencode`. 在调用 api 时, 才需要对参数做 `urlencode`

- - -

## sendn (暂不开通)

发送一个短信模板给多个用户, 每个用户对应一个替换变量.
    
**URL**
```
http://sendcloud.sohu.com/smsapi/sendn
```

**返回数据格式**
```
json
```

**HTTP请求方式**    
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

*vars格式示例:*

    {"name": "lucy"} or {"%name%": "lucy"}

*tos格式示例:*
    
    [{"phone": "13111111111", "vars": {"%name%": "name1"}}, {"phone": "13122222222", "vars": {"%name%": "name2"}}]

`注意`: 

1. 参数 vars 可能含有特殊字符, 记得 `urlencode`

2. vars 所传递的变量的值, 长度不能超过 32 个字符

3. HTTP 设置头域 `Content-Type: application/x-www-form-urlencoded`

4. 生成签名时, 参数不要使用 `urlencode`. 在调用 api 时, 才需要对参数做 `urlencode`

- - -
    
## timestamp
     
获取服务器时间戳

**URL**
```
http://sendcloud.sohu.com/smsapi/timestamp/get
```

**返回数据格式**
```
json
```

**HTTP请求方式**    
```
GET
```
    
**参数说明**

无

- - - 

## 返回码

短信 API 返回的结果是 JSON 格式, 示例如下: 

```
# 请求成功
{
    "message":"请求成功",
    "info":{},
    "result":true,
    "statusCode":200
}

# 手机号格式错误
{
    "message":"手机号格式错误",
    "info":{},
    "result":false,
    "statusCode":412
}

# 部分成功
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

```
* result: API 请求是否成功
* statusCode: API 返回码
* message: API 返回码的中文解释
* info: 更多信息, 比如: *部分成功*则返回具体失败信息, *获取时间戳*则返回时间戳的值

|API 返回码|含义|
|:---------|:---|
|200|发送成功|
|311|部分成功|
|401|短信内容不能为空|
|411|手机号不能为空|
|412|手机号格式错误|
|413|有重复的手机号|
|421|签名参数错误|

