
当用户选择退订邮件时, 他的邮件地址就会被添加到此列表中.

在此列表中的邮件地址, 都不会再被发送邮件.
    
你可以对此列表进行查询, 删除, 添加操作
     
- - -

##查询
     
**URL**
```  
http://sendcloud.sohu.com/webapi/unsubscribes.get.json
```
   
**HTTP请求方式**   
```bash
post    get
```
    
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|子账号|
|api_key|string|是|密码|
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|start_date|string|*|开始日期, 格式为`yyyy-MM-dd`|
|end_date|string|*|结束日期, 格式为`yyyy-MM-dd`|
|email|string|*|查询该地址在取消订阅列表中的详情|
|api_user_list|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`api_user_list=a;b;c`|
|label_id_list|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`label_id_list=a;b;c`|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|

提示:

1. 如果指定时间区间, 则是查询此时间区间内的取消订阅列表. 注意: **start_date 与 end_date 的组合** 或者 **days 参数**, 二者取一. 
2. 如果指定email, 则是查询此地址在取消订阅列表中的详细信息. 注意: 此时, 时间区间参数失效.
    
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
   
##添加
    
**URL**
```
http://sendcloud.sohu.com/webapi/unsubscribes.add.json
```
    
**HTTP请求方式** 
```bash
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
            "email": "abc@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "create_at": "2014-11-19 10:57:24"
        }
    ]
}
```
- - - 
    
##删除
    
**URL**
```
http://sendcloud.sohu.com/webapi/unsubscribes.delete.json
```
    
**HTTP请求方式** 
```bash
post    get 
```
    
**参数说明**

|参数|类型|必须|说明|
|:---|:---|:---|:---|  
|api_user|string|是|子账号|
|api_key|string|是|密码|
|start_date|string|*|开始日期, 格式为`yyyy-MM-dd`|
|end_date|string|*|结束日期, 格式为`yyyy-MM-dd`|
|email|string|*|删除该地址|

提示:

1. 如果指定时间区间, 则是删除此时间区间内的所有退信地址.
2. 如果指定email, 则是在退信列表中删除此地址. 注意: 此时, 时间区间参数失效.
    
    
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

