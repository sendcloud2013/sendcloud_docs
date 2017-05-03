
API_USER 是用户在 SendCloud 中发信的验证帐号.
    
你可以使用 API 进行 API_USER 的查询, 添加操作

- - -

## 查询 ( 批量查询 )
    
通过此接口查询 API_USER 的基本信息
    
**URL**
```
http://api.sendcloud.net/apiv2/apiuser/list
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|emailType|int|否|API_USER 的邮件类型: 0(触发), 1(批量)|
|cType|int|否|API_USER 的类型: 0(测试), 1(普通)|
|domainName|string|否|API_USER 绑定的域名名称|
    
**请求示例**
```
http://api.sendcloud.net/apiv2/apiuser/list?apiUser=***&apiKey=***&emailType=1&domainName=mail.liubida.cn 
```
    
**返回值说明**

|参数|说明|
|:---|:---|
|name|API_USER 名称|
|cType|API_USER 类型|
|emailType|API_USER 的邮件类型|
|domainName|API_USER 绑定的域名|
|neteaseSender|API_USER 绑定的网易诚信 Sender |
|click|是否开启点击追踪: 0(关闭), 1(开启)|
|open|是否开启阅读追踪: 0(关闭), 1(开启)|
|unsubscribe|是否开启取消订阅: 0(关闭), 1(开启)|

**返回值示例**
```
{
    statusCode: 200,
    info: {
        count: 1,
        dataList: [{
            name: "***",
            cType: "非测试",
            emailType: "触发",
            domainName: "delong.com",
            neteaseSender: "",
            click: 1,
            open: 1,
            unsubscribe: 1
        }]
    },
    message: "请求成功",
    result: true
}
```

- - -

## 添加
    
通过此接口添加 API_USER
    
**URL**
```
http://api.sendcloud.net/apiv2/apiuser/add
```
    
**HTTP请求方式**
```bash
post    get
```
    
**参数说明**
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|apiUser|string|是|API_USER|
|apiKey|string|是|API_KEY|
|name|string|是|API_USER 的名称|
|emailType|int|是|API_USER 的邮件类型: 0(触发), 1(批量)|
|domainName|string|是|API_USER 绑定的域名名称|
|open|int|否|是否开启阅读追踪: 0(关闭), 1(开启). 默认: 开启|
|click|int|否|是否开启点击追踪: 0(关闭), 1(开启).|
|unsubscribe|int|否|是否开启取消订阅: 0(关闭), 1(开启). 默认: 开启|

**说明**
```
1. 开启点击追踪(click), 需要绑定的域名通过了 `cname` 配置验证
2. 取消订阅追踪(unsubscirbe), 系统对于 免费用户 会强制开启.
```
    
**请求示例**
```
http://api.sendcloud.net/apiv2/apiuser/add?apiUser=***&apiKey=***&name=liubida&emailType=1&domainName=mail.liubida.cn 
```
    
**返回值说明**

|参数|说明|
|:---|:---|
|name|API_USER 名称|
|nameKey|API_KEY 名称|
|cType|API_USER 类型|
|emailType|API_USER 的邮件类型|
|domain|API_USER 绑定的域名|
|neteaseSender|API_USER 绑定的网易诚信 Sender |
|click|是否开启点击追踪|
|open|是否开启阅读追踪|
|unsubscribe|是否开启取消订阅|

**返回值示例**
```
{
    statusCode: 200,
    info: {
        total: 1,
        data: {
            name: "***",
            nameKey:"********",
            cType: "非测试",
            emailType: "触发",
            domain: "delong.com",
            neteaseSender: "",
            click: 1,
            open: 1,
            unsubscribe: 1
        }
    },
    message: "请求成功",
    result: true
}
```

