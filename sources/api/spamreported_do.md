##垃圾举报查询
    
查询垃圾邮件的举报信息
     
- - -
###查询
     
**URL**
```  
https://sendcloud.sohu.com/webapi/spamReported.get
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
|days|int|否|过去days天内的统计数据(包含今天), 必须大于0| 
|start_date|date|否|开始日期, 格式必须为yyyy-MM-dd, 对应时间必须在参数end_date对应时间之前|
|end_date|date|否|结束日期, 格式必须为yyyy-MM-dd, 对应时间必须在参数start_date对应时间之后|
|start|int|否|返回数据的起始位置, 如果不设置, 默认为0|
|limit|int|否|限制返回数据的个数, 如果不设置, 默认为100个|
|email|string|否|地址|查询该地址垃圾举报的详情|
    
请求示例:    
```
http://sendcloud.sohu.com/webapi/spamReported.get.json?api_user=***&api_key=***
```
    
**返回值说明**
    
无    
    
**返回值示例**    
```
{
    "message": "success",
    "bounces": [
        {
            "email": "123@qq.com",
            "reason": "spam reported",
            "create_at": "2014-11-10 18:26:30"
        }
    ]
}
```

