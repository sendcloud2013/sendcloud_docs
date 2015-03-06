##按小时统计   
    
查询按小时统计的邮件数据
    
- - -
    
###查询
    
**URL**    
```
https://sendcloud.sohu.com/webapi/statsHour.get 
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
|api_user_list|string|否|获取指定api_user下的统计数据,api_user列表，多个用";"分开|
|label_id_list|string|否|获取指定标签下的统计数据,标签列表,多个用";"分开|
    
提示:参数中必须包含【start_date与end_date的组合】或days
    
请求示例:    
```
http://sendcloud.sohu.com/webapi/statsHour.get.json?api_user=***&api_key=***&days=20
```
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|sendHour|某个小时的发送统计，正整数，范围从0到23|
|apiUser|子账号|
|request|请求总数|
|deliveredNum|送达数量|
|clickNum|点击数量|
|openNum|打开数量|
|bounceNum|弹回数量|
|spamReportedNum|垃圾举报数量|
|unsubscribeNum|取消订阅数量|
|uniqueOpensNum|独立打开数量|
|uniqueClicksNum|独立点击数量|
|invalidEmailsNum|无效邮件数量|
|delivered_percent|送达比例|
|click_percent|点击比例|
|open_percent|打开比例|
|bounce_percent|弹回比例|
|spamReported_percent|垃圾举报比例|
|unsubscribe_percent|取消订阅比例|
|uniqueOpens_percent|独立打开比例|
|uniqueClicks_percent|独立点击比例|
|invalidEmails_percent|无效邮件比例|
|domain|域名|
|labelName|标签名|
|labelId|标签ID|
    
返回值示例:
```
{
    "message": "success",
    "stats": [
        {
            "sendDate": "2014-10-31",
            "apiUser": "api_user",
            "labelId": 0,
            "sendHour": 11,
            "request": 1,
            "deliveredNum": 1,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "uniqueClicksNum": 0,
            "invalidEmailsNum": 0,
            "delivered_percent": 100,
            "click_percent": 0,
            "open_percent": 0,
            "bounce_percent": 0,
            "spamReported_percent": 0,
            "unsubscribe_percent": 0,
            "uniqueOpens_percent": 0,
            "uniqueClicks_percent": 0,
            "invalidEmails_percent": 0
        }
    ]
}
```

