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
```bash
GET POST    
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

2. vars 所传递的变量的值, 长度不能超过 32 个字符, 变量中不能含有 HTTP 链接

3. 生成签名时, 参数不要使用 `urlencode`. 在调用 api 时, 才需要对参数做 `urlencode`

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
```bash
GET POST    
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

2. vars 所传递的变量的值, 长度不能超过 32 个字符, 变量中不能含有 HTTP 链接

3. 生成签名时, 参数不要使用 `urlencode`. 在调用 api 时, 才需要对参数做 `urlencode`

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
```bash
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
|:-------------|:---|
|200|请求成功|
|311|部分号码请求成功|
|411|手机号不能为空|
|412|手机号格式错误|
|413|有重复的手机号|
|421|签名参数错误|
|422|签名错误|
|431|模板不存在|
|432|模板未提交审核或者未审核通过|
|433|模板ID不能为空|
|441|替换变量格式错误|
|451|定时发送时间的格式错误|
|452|定时发送时间早于服务器时间, 时间已过去|
|461|时间戳无效, 与服务器时间间隔大于6秒|
|471|smsUser不存在|
|472|smsUser不能为空|
|473|没有权限, 免费用户不能发送短信|
|474|用户不存在|
|481|手机号和替换变量不能为空|
|482|手机号和替换变量格式错误|
|483|替换变量长度不能超过32个字符|
|497|五分钟内给当前手机号发信太多|
|498|一天内给当前手机号发信太多|
|499|账户额度不够|
|501|服务器异常|

|SMSHook 错误码(statusCode)|含义|
|:-------------|:---|
|410|处理失败, 手机号在全局拦截列表|
|420|处理失败, 手机号在局部拦截列表|
|430|处理失败, 取消订阅|
|440|处理失败, 关键词过滤|
|450|处理失败, 替换变量错误|
|460|处理失败, 短信内容不能超过500个字符|
|500|发送失败, 手机空号|
|510|发送失败, 手机停机|
|550|发送失败, 该模板内容被投诉|
|580|发送失败, 手机关机|
|590|发送失败, 其他原因|

