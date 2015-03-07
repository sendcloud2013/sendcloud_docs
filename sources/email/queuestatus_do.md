##队列状态查询    
    
查询发送队列的状态    
    
- - -    
    
###查询    

**URL**    
```
https://sendcloud.sohu.com/webapi/queueStatus.get
```    
    
**返回数据格式**    
```
json
```    
    
**HTTP请求方式**    
```
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|子账号|
|api_key|string|是|密码|
    

    
**返回值说明**    
    

|参数|说明|
|:---|:---|
|apiUser|api_user名称|
|domain|域名|
|isStop|该队列是否暂停|
|reason|如果isStop=true，该字段代表队列暂停原因|
|timeout|队列被暂停的剩余时间,单位:秒|
|unRequestNum|未处理的邮件数|
    
返回值示例:
```  
{
    "message": "success",
    "queueStatus": [
        {
            "apiUser": "api_user",
            "domain": "sendcloud.im",
            "isStop": false,
            "reason": "",
            "timeout": null,
            "unRequestNum": 0
        },
        {
            "apiUser": "api_user",
            "domain": "163.com",
            "isStop": false,
            "reason": "",
            "timeout": null,
            "unRequestNum": 0
        }
    ]
}
```
