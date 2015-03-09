##邮件发送

邮件发送 API 应该是用户使用最频繁的 API.    

- - -

### 普通发送

**URL**
```  
http://sendcloud.sohu.com/webapi/mail.send.json
```
   
**返回数据格式** 
```
json
```
     
**HTTP请求方式** 
```
post | get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|API_USER|  
|api_key|string|是|API_KEY|  
|from|string|是|发件人地址. from 和发信域名, 会影响是否显示'代发', 详见TODO 代发|  
|fromname|string|否|发件人名称. 显示如: `ifaxin客服支持 <support@ifaxin.com>`|  
|to|string|是|收件人地址. 多个地址使用';'分隔, 如`123@qq.com;456@qq.com`|  
|bcc|string|否|密送地址. 多个地址使用';'分隔|  
|cc|string|否|抄送地址. 多个地址使用';'分隔|  
|subject|string|是|标题. 不能为空|  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  
|||是否||  



|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|子账号|
|api_key|string|是|密码|
|days|int|否|过去days天内的统计数据(包含今天), 必须大于0|
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd, 对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd, 对应时间必须在参数start_date对应时间之后|
|api_user_list|string|否|获取指定api_user下的统计数据, 格式为列表, 多个api_user用";"分开|
|label_id_list|string|否|获取指定标签下的统计数据, 格式为列表, 多个标签用";"分开|  
|start|int|否|返回数据的起始位置, 如果不设置, 默认为0|
|limit|int|否|限制返回数据的个数. 必须大于0小于100, 如果不设置, 默认为100个| 
|email|string|否|查询该地址在取消订阅列表中的详情|
    
提示：参数中必须包含【email】或【start_date与end_date的组合】或【days】或【start与limit的组合】.
    
请求示例:
```
http://sendcloud.sohu.com/webapi/unsubscribes.get.json?api_user=***&api_key=***&api_user_list=[***;***]&days=100&start=0&limit=3 
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
http://sendcloud.sohu.com/webapi/unsubscribes.delete
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
|start_date|date|否|开始日期,格式必须为yyyy-MM-dd，对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期,格式必须为yyyy-MM-dd，对应时间必须在参数start_date对应时间之后|
|email|string|否|要删除的地址|
    
提示:参数中必须包含【email】或【start_date与end_date组合】
    
请求示例:
```
http://sendcloud.sohu.com/webapi/unsubscribes.delete.json?api_user=***&api_key=*** &email=test@sendcloud.com 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
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
http://sendcloud.sohu.com/webapi/unsubscribes.add
```
    
**HTTP请求方式** 
```
POST    get 
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


