
当用户选择退订邮件时, 他的邮件地址就会被添加到此列表中.

在此列表中的邮件地址, 都不会再被发送邮件.
    
你可以对此列表进行查询, 删除, 添加操作
     
- - -

##查询
     
**URL**
```  
http://api.sendcloud.net/apiv2/unsubscribe/list
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
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)| 
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|email|string|*|查询该地址在取消订阅列表中的详情|
|apiUserList|string|否|获取指定 API_USER 的统计数据, 多个 API_USER 用`;`分开, 如:`apiUserList=a;b;c`|
|labelIdList|string|否|获取指定标签下的统计数据, 多个标签用`;`分开, 如:`labelIdList=a;b;c`|
|start|int|否|查询起始位置, 取值区间 [0-], 默认为 0|
|limit|int|否|查询个数, 取值区间 [0-100], 默认为 100|

提示:

1. 如果指定时间区间, 则是查询此时间区间内的取消订阅列表. 注意: **startDate 与 endDate 的组合** 或者 **days 参数**, 二者取一. 
2. 查询的天数不超过三个月.
3. 如果指定email, 则是查询此地址在取消订阅列表中的详细信息. 注意: 此时, 时间区间参数失效.
    
请求示例:
```
http://api.sendcloud.net/apiv2/unsubscribe/list?apiUser=***&apiKey=***&apiUserList=[***;***]&days=100&start=0&limit=3 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|email|邮件地址|
|domain|邮件地址对应的域名|
|labelId|标签ID|
|apiUser|apiUser名称|
|unsubscribeTime|取消订阅的发生时间|
    
返回值示例:
```
{
    "statusCode":200,
    "info":{
      "dataList": [
        {
            "email": "1071117612@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "unsubscribeTime": "2014-09-01 11:20:31"
        },
        {
            "email": "112347404@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "unsubscribeTime": "2014-09-01 18:00:17"
        },
        {
            "email": "1131480723@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "unsubscribeTime": "2014-10-08 15:22:14"
        }
    ],
   "count":1
 },
  "result":true,
  "message":"请求成功"
}
```

- - -
   
##添加
    
**URL**
```
http://api.sendcloud.net/apiv2/unsubscribe/add
```
    
**HTTP请求方式** 
```bash
POST    get 
```
      
**参数说明**    
    
|参数|类型|必须|说明|
|:---|:---|:---|:---| 
|apiUser|string|是|API_USER| 
|apiKey|string|是|API_KEY| 
|email|string|是|要添加的地址|
    
请求示例:
    
```
http://api.sendcloud.net/apiv2/unsubscribe/add?apiUser=***&apiKey=***&email=abc@qq.com  
```
    
**返回值说明**      
    
|参数|类型| 
|:---|:---|
|email|邮件地址|
|domain|邮件地址对应的域名|
|labelId|标签ID|
|apiUser|API_USER|
|unsubscribeTime|取消订阅发送的时间|
    
返回值示例:
```
{
    "statusCode":40401,
    "info": {
    "data": 
        {
            "email": "abc@qq.com",
            "domain": "qq.com",
            "labelId": 0,
            "apiUser": "api_user",
            "unsubscribeTime": "2014-11-19 10:57:24"
        }
     }
    "result":true,
    "message":"请求成功"
    
}
```
- - - 
    
##删除
    
**URL**
```
http://api.sendcloud.net/apiv2/unsubscribe/delete
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
|days|int|*|过去 days 天内的统计数据 (`days=1`表示今天)|
|startDate|string|*|开始日期, 格式为`yyyy-MM-dd`|
|endDate|string|*|结束日期, 格式为`yyyy-MM-dd`|
|email|string|*|删除该地址|

提示:

1. 如果指定时间区间, 则是删除此时间区间内的所有退信地址. 注意: **startDate 与 endDate 的组合** 或者 **days 参数**, 二者取一. 
2. 时间区间不能超过三个月.
3. 如果指定email, 则是在退信列表中删除此地址. 注意: 此时, 时间区间参数失效.
    
    
请求示例:
```
http://api.sendcloud.net/apiv2/unsubscribe/delete?apiUser=***&apiKey=*** &email=test@sendcloud.com 
```
    
**返回值说明**    
    
|参数|说明|
|:---|:---|
|count|成功删除的邮件地址个数|
    
返回值示例:
```
{   
    "statusCode":200,
    "info":{"count":1},
    "message":"请求成功",
    "result":true
}
```

