##取消订阅管理
    
取消订阅列表的查询、删除、添加操作
     
- - -
###查询
     
**URL**
```  
https://sendcloud.sohu.com/webapi/unsubscribes.get
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
|days|int|否|过去days天内的统计数据(包含今天),必须大于0|
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd，对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd，对应时间必须在参数start_date对应时间之后|
|api_user_list|string|否|获取指定api_user下的统计数据,api_user列表，多个元素用";"分开|
|label_id_list|string|否|获取指定标签下的统计数据,标签列表,多个元素用";"分开|  
|start|int|否|返回数据的起始位置, 如果不设置，默认为0|
|limit|int|否|限制返回数据的个数。必须大于0,小于100,如果不设置，默认为100个| 
|email|string|否|地址|查询某地址在取消订阅列表中的详情|
    
提示：参数中必须包含email或【start_date与end_date的组合】或days或【start与limit的组合】.
    
请求示例:
```
http://sendcloud.sohu.com/webapi/unsubscribes.get.json?api_user=***&api_key=***&days=100&start=0&limit=3 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|email|邮件地址|
|domain|邮件地址对应的域名|
|labelId|标签ID|
|apiUser|api_user名称|
|create_at|取消订阅的发生时间|
    
返回值示例:
```
{
    "message": "success",
    "unsubscribes": [
        {
            "email": "1071117612@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "create_at": "2014-09-01 11:20:31"
        },
        {
            "email": "112347404@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "create_at": "2014-09-01 18:00:17"
        },
        {
            "email": "1131480723@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "create_at": "2014-10-08 15:22:14"
        }
    ]
}
```
    
- - - 
    
###删除列表成员
    
**URL**
```
https://sendcloud.sohu.com/webapi//unsubscribes.delete
```
    
**返回数据格式**
```
json
```
    
**HTTP请求方式** 
```
post
```
    
**参数说明**

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|子账号|
|api_key|string|是|密码|
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd，对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd，对应时间必须在参数start_date对应时间之后|
|email|string|否|地址|要删除的地址|
    
提示:参数中必须包含email或【start_date与end_date】组合
    
请求示例:
```
http://sendcloud.sohu.com/webapi/unsubscribes.delete.json?api_user=***&api_key=*** &email=test@sendcloud.com 
```
    
**返回值说明**    
    
|参数|说明|
|del_count|成功删除的邮件地址个数|
    
返回值示例:
```
{
    "message": "success",
    "del_count": 1
}
```

- - -
   
###添加列表成员
    
**URL**
```
https://sendcloud.sohu.com/webapi/unsubscribes.add
```
    
**HTTP请求方式** 
```
POST
```
      
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---| 
|api_user|string|是|子账号| 
|api_key|string|是|密码| 
|email|string|否|要添加的地址|
    
请求示例:
    
```
http://sendcloud.sohu.com/webapi/unsubscribes.add.json?api_user=***&api_key=***&email=abc@qq.com  
```
    
**返回值说明**      
    
|参数|类型|必须|说明|  
|:---|:---|:---|:---| 
|email|邮件地址|
|domain|邮件地址对应的域名|
|labelId|标签ID|
|apiUser|子账号|
|create_at|取消订阅发送的时间|
    
返回值示例:
```
{
    "message": "success",
    "unsubscribes": [
        {
            "email": "abc@qq.com.164.com",
            "domain": "qq.com.164.com",
            "labelId": 0,
            "apiUser": "api_user",
            "create_at": "2014-11-19 10:57:24"
        }
    ]
}
```





