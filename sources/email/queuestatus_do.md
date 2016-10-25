
- - -

## 查询    
    
查询发送队列的实时状态    

**URL**    
```
http://sendcloud.sohu.com/webapi/queueStatus.get.json
```    
    
**HTTP请求方式**    
```bash
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|
|api_user|string|是|API_USER|
|api_key|string|是|API_KEY|
    
**返回值说明**    

|参数|说明|
|:---|:---|
|apiUser|api_user名称|
|domain|域名|
|isStop|API_USER 下到域名的发送队列是否暂停|
|reason|如果 `isStop=true`, 该字段表示队列暂停的原因|
|timeout|队列被暂停的时间, 单位:秒|
|unRequestNum|队列中未处理的邮件数|
    
返回值示例:
```  
{
    "message": "success",
    "queueStatus": [
        {
            "apiUser": "api_user",
            "domain": "qq.com",
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
