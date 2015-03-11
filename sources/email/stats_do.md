##发送数据统计
    
查询按天统计的邮件数据
    
- - -
    
###查询
    
**URL**    
```
https://sendcloud.sohu.com/webapi/stats.get.json
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
|list|boolean|否|如果为true, 获取用户的所有类别, 其它设置或者不设置默认为false|
|api_user_list|string|否|获取指定api_user下的统计数据, 格式为列表, 多个api_user用";"分开|
|aggregate|int|否|如果为1, 累加统计数据, 其它设置或者不设置默认不需要累加结果|
|label_id_list|string|否|获取指定标签下的统计数据, 格式为列表, 多个标签用";"分开|
|domain_list|string|否|获取指定域名下的统计数据, 格式为列表, 多个域名用";"分开|
    
提示:    
1.获取一段时间的统计数据时, 必须指定时间区间, 即【start_date与end_date的组合】或者【days参数】必须设置, 并且查询的天数不超过100.    
2.当aggregate为1时, 会将时间区间内的数据累加.
    
请求示例:    
```
https://sendcloud.sohu.com/webapi/stats.get.xml?api_user=***&api_key=***&days=2&label_id_list=[***;***]
```
    
    
    
**返回值说明**
    
|参数|说明|
|:---|:---|
|sendDate|发送日期|
|request|请求总数|
|apiUser|子账号|
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
            "sendDate": "2014-11-11",
            "apiUser": "api_user",
            "apiUserId": 53584,
            "labelId": 0,
            "labelName": null,
            "domain": "qq.com",
            "request": 4,
            "deliveredNum": 0,
            "clickNum": 0,
            "openNum": 0,
            "bounceNum": 0,
            "spamReportedNum": 0,
            "unsubscribeNum": 0,
            "uniqueOpensNum": 0,
            "invalidEmailsNum": 4,
            "delivered_percent": 0,
            "click_percent": 0,
            "open_percent": 0,
            "bounce_percent": 0,
            "spamReported_percent": 0,
            "unsubscribe_percent": 0,
            "uniqueOpens_percent": 0,
            "uniqueClicks_percent": 0,
            "invalidEmails_percent": 100
        }
    ]
}
```
    
    
